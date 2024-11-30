document.addEventListener('DOMContentLoaded', () => {
    const initializeMap = () => {
        const map = L.map('map').setView([51.505, -0.09], 13);

        const streetLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        });

        const satelliteLayer = L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/satellite-v9/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoiYmFzZTgzIiwiYSI6ImNsd2h0eWg3cDBpdHIybm9jNHM5MWRycTMifQ.zjoSveszDD78H00-xBU4zQ', {
            attribution: '&copy; <a href="https://www.mapbox.com/about/maps/">Mapbox</a> contributors'
        });

        streetLayer.addTo(map);

        const baseLayers = {
            "Street": streetLayer,
            "Satellite": satelliteLayer
        };

        L.control.layers(baseLayers).addTo(map);

        // Initialize the geocoder control with autocomplete
        const geocoder = L.Control.geocoder({
            defaultMarkGeocode: false
        }).on('markgeocode', function(e) {
            const bbox = e.geocode.bbox;
            const poly = L.polygon([
                bbox.getSouthEast(),
                bbox.getNorthEast(),
                bbox.getNorthWest(),
                bbox.getSouthWest()
            ]).addTo(map);
            map.fitBounds(poly.getBounds());
        }).addTo(map);

        return map;
    };

    const addMapClickEvent = (map) => {
        map.on('click', (event) => {
            const { lat, lng } = event.latlng;
            L.popup()
                .setLatLng(event.latlng)
                .setContent(`You clicked the map at ${lat.toFixed(2)}, ${lng.toFixed(2)}`)
                .openOn(map);
        });
    };

    const createLocateButton = () => {
        const button = document.createElement('button');
        button.textContent = 'Locate Me';
        button.style.position = 'absolute';
        button.style.top = '10px';
        button.style.right = '10px';
        button.style.zIndex = '1000';
        document.body.appendChild(button);
        return button;
    };

    const addLocateButtonEvent = (map, button) => {
        button.addEventListener('click', () => {
            map.locate({ setView: true, maxZoom: 16 });
        });

        map.on('locationfound', (event) => {
            const radius = event.accuracy / 2;
            L.marker(event.latlng).addTo(map)
                .bindPopup(`You are within ${radius} meters from this point`).openPopup();
            L.circle(event.latlng, radius).addTo(map);
        });

        map.on('locationerror', (event) => {
            alert(event.message);
        });
    };

    const addSearchEvent = (map) => {
        const searchButton = document.getElementById('search-button');
        const searchInput = document.getElementById('search-input');

        searchButton.addEventListener('click', async () => {
            const query = searchInput.value;
            if (!query) {
                alert('Please enter a location to search for.');
                return;
            }

            try {
                const response = await fetch(`/api/geocoding/search?query=${query}`);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const results = await response.json();
                if (results.length === 0) {
                    alert('No results found.');
                    return;
                }

                const { lat, lon, displayName } = results[0];
                map.setView([lat, lon], 13);
                L.marker([lat, lon]).addTo(map)
                    .bindPopup(displayName)
                    .openPopup();
            } catch (error) {
                console.error('Error occurred while searching for location:', error);
                alert('An error occurred while searching for the location.');
            }
        });
    };

    try {
        const map = initializeMap();
        addMapClickEvent(map);
        const locateButton = createLocateButton();
        addLocateButtonEvent(map, locateButton);
        addSearchEvent(map);

        // Automatically center the map on the user's current location
        map.locate({ setView: true, maxZoom: 16 });
    } catch (error) {
        console.error('An error occurred while initializing the map:', error);
    }
});