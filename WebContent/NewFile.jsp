<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <title>显示地图</title>
    <style type="text/css">
        html, body{ height:100%; margin:0; padding:0;}
        #mapDiv{ width:500px; height:400px; background-color:#EFF5FF;}
    </style>
    <script src="http://api.shipxy.com/apdll/ap.dll?api=map&amp;key=1F6D701272402D1E7D8D316CCE519123&amp;ver=1.3" type="text/javascript"></script>
    <script type="text/javascript">
        var map;
        window.onload = function () {
            var mapOptions = new shipxyMap.MapOptions();
            mapOptions.center = new shipxyMap.LatLng(32, 122);
            mapOptions.zoom = 5;
            mapOptions.mapType = shipxyMap.MapType.CMAP;
            //mapDiv是一个DIV容器的id，用于放置flash地图组件
            map = new shipxyMap.Map('mapDiv', mapOptions); //创建地图实例
            //地图初始化完毕
            shipxyMap.mapReady = function () {
                //地图初始化完毕才能操作 地图其他的接口
            }
        }
    </script>
</head>
<body>
    <div id="mapDiv"></div>
</body>
</html>
