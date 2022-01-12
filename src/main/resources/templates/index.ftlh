<#-- @ftlvariable name="version" type="java.lang.String" -->
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="noindex,nofollow">
    <meta name="googlebot" content="noindex">
    <title>React REST - SpringBoot API template</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <style>
        .jumbotron {
            padding: 2rem 1rem;
            margin: 1rem 0 5rem 0;
            background-color: #d3d3e7;
            border-radius: .3rem;
            display: block;
        }

        .jumbotitle {
            font-weight: bold;
        }
    </style>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col">
            &nbsp;
        </div>
        <div class="col-10">
            <div class="container mt-5">
                <div class="jumbotron border">
                    <h2 class="display-5 jumbotitle text-primary">React REST</h2>
                    <p class="lead">A reactive REST API Template written with SpringBoot</p>
                    <hr class="my-4"/>
                    <div>
                        <small class="text-secondary">React REST v.<span id="version">${version}</span></small>
                    </div>
                    <div class="mt-4">
                        <a class="btn btn-primary btn-lg" href="/swagger-ui.html" role="button">API
                            Documentation</a>
                        &nbsp;
                        <a class="btn btn-success btn-lg" href="/h2" role="button">H2 DB Console</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col">
            &nbsp;
        </div>
    </div>
</div>

<!-- Bootstrap JavaScript Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>

</html>
