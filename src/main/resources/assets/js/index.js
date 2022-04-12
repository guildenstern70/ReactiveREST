/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

(function() {
    fetch('http://localhost:8080/api/common/version')
        .then( res => res.json() )
        .then(response => {
            console.log(JSON.stringify(response));
            document.getElementById('version').innerHTML = response.version;
        })
        .catch(error => console.error('Error:', error));
})();
