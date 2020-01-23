<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Dashboard - Brand</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
    <link rel="stylesheet" href="css/filterStyle.css">
    <link rel="stylesheet" href="css/newDevStatsStyle.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
    <script src="scripts/newDevStatsScript.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.debug.js"></script>
</head>

<body id="page-top" style="background-color: #284c67;">
<jsp:include page="header.jsp" />
<div class="row" id="firstRow">
    <div class="col-3" id="divProfileMenu">
        <jsp:include page="profileMenu.jsp"></jsp:include>
    </div>
    <div class="col" id="wrapper">
    <div class="d-flex flex-column" id="content-wrapper" style="background-color: rgba(248,249,252,0);">
        <div id="content">
            <div class="container-fluid">
                <div class="d-sm-flex justify-content-between align-items-center mb-4">
                    <h3 class="mb-0" style="color: rgb(207,204,204);">Statistiche generali come sviluppatore</h3>
                    <div class="filter border rounded" id="filter">
                        <form action="/filterDevStats" method="get">
                            <select id="anno" name="anno">
                                <option selected="selected">Anno</option>
                                <option>2016</option>
                                <option>2017</option>
                                <option>2018</option>
                                <option>2019</option>
                                <option>2020</option>
                            </select>
                            <button type="submit" id="btnDev" class="border rounded">Filtra</button>
                        </form>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 2%;">
                    <div class="col-md-6 col-xl-3 m-auto mb-4">
                        <div class="card shadow border-left-primary py-2">
                            <div class="card-body">
                                <div class="row align-items-center no-gutters">
                                    <div class="col mr-2">
                                        <div class="text-uppercase text-primary font-weight-bold text-xs mb-1">
                                            <span>Media guadagno</span>
                                        </div>
                                        <div class="text-dark font-weight-bold h5 mb-0">
                                            <span>${averageMoneyEarned}</span>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xl-3 m-auto mb-4">
                        <div class="card shadow border-left-success py-2">
                            <div class="card-body">
                                <div class="row align-items-center no-gutters">
                                    <div class="col mr-2">
                                        <div class="text-uppercase text-success font-weight-bold text-xs mb-1">
                                            <span>Media numero giochi venduti</span>
                                        </div>
                                        <div class="text-dark font-weight-bold h5 mb-0">
                                            <span>${averageSoldGames}</span>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-wallet fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-7 col-xl-8">
                        <div class="card shadow mb-4">
                            <div class="card-header d-flex justify-content-between align-items-center">
                                <h6 class="text-primary font-weight-bold m-0">Giochi venduti divisi per ${meseAnno}</h6>
                            </div>
                            <div class="card-body">
                                <div class="chart-area">
                                    <canvas id="soldGamesChart" class="canvas-size"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5 col-xl-4">
                        <div class="card shadow mb-4">
                            <div class="card-header d-flex justify-content-between align-items-center">
                                <h6 class="text-primary font-weight-bold m-0">Totale guadagnato diviso per ${meseAnno}</h6>
                            </div>
                            <div class="card-body">
                                <div class="chart-area">
                                    <canvas style="display: block; width: 324px; height: 320px" id="moneyEarnedChart" class="canvas-size"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="footer.html" />
</body>


<script>
    var ctx = document.getElementById('soldGamesChart').getContext('2d');
    var chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ${soldGameKeys},
            datasets: [{
                label: 'Numero giochi venduti',
                backgroundColor: 'rgb(173, 216, 240)',
                borderColor: 'rgb(255, 165, 0)',
                data: ${soldGameValues}
            }]
        },
        options: {}
    });
    var ctx = document.getElementById('moneyEarnedChart').getContext('2d');
    var chart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ${moneyEarnedKeys},
            datasets: [{
                label: 'Guadagno totale',
                backgroundColor: ["#b2a715", "#d6116f", "#3ca64a", "#781ff4", "#8354fd", "#6d3444", "#b2a715", "#96a906", "#3ca64a", "#df2275"],
                borderColor: 'rgb(255, 165, 0)',
                data: ${moneyEarnedValues}
            }]
        },
        options: {}
    });
</script>
</html>