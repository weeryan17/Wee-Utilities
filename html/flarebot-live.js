/*
 * I got this from flarebots website.
 * I'm using it as an example for me making stuff
 */
var realtimeGraph;
var date = new Date();
var graphSize = 100;
$(document).ready(function() {
    var odometer = new Odometer({
        el: $('#odometer')[0],
        value: 0,
        theme: 'flarebot'
    });
    odometer.render();
    var updateGraph = function(amount=1) {
        $.get('../api/lookup.php?o=guilds&a=' + amount, function(data) {
            var json = data['lookup-results'];
            json.reverse();
            var guilds = parseInt(json[0]);
            if (amount == 1) {
                var x = new Date().getTime();
                var shift = realtimeGraph.series[0].data.length >= graphSize;
                realtimeGraph.series[0].addPoint([x, guilds], true, shift);
            } else {
                for (var i = 0; i < amount; i++) {
                    var x = new Date().getTime();
                    var shift = realtimeGraph.series[0].data.length >= graphSize;
                    realtimeGraph.series[0].addPoint([x, parseInt(json[i])], true, shift);
                }
            }
            odometer.update(guilds);
        });
    }
    setTimeout(function() {
        updateGraph(3);
    }, 500);
    setInterval(function() {
        updateGraph();
    }, 30000);
    realtimeGraph = Highcharts.chart('container', {
        chart: {
            type: 'spline',
            zoomType: 'x',
            animation: Highcharts.svg,
            backgroundColor: 'transparent',
            style: {
                color: '#000'
            }
        },
        plotOptions: {
            series: {
                lineWidth: 3,
                marker: {
                    enabled: false
                }
            }
        },
        title: {
            text: ''
        },
        xAxis: {
            title: {
                text: 'Time',
                style: {
                    color: 'white'
                }
            },
            labels: {
                style: {
                    color: 'white'
                }
            },
            type: 'datetime',
            tickPixelInterval: 150,
            gridLineColor: 'white',
            lineColor: 'white'
        },
        yAxis: {
            title: {
                text: 'Guilds',
                style: {
                    color: 'white'
                }
            },
            labels: {
                enabled: true,
                style: {
                    color: 'white'
                }
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: 'white'
            }],
            gridLineWidth: 0,
            allowDecimals: false
        },
        tooltip: {
            formatter: function() {
                return '<b>' + this.series.name + '</b><br/>' + Highcharts.dateFormat('%H:%M:%S', this.x) + '<br/>' + numberWithCommas(this.y);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'Servers',
            color: 'cyan',
            data: (function() {}())
        }],
        credits: {
            enabled: false
        }
    });
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
});
