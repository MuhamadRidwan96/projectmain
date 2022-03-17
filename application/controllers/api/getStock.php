
<?php

require_once'connection_db.php';

$order = "SELECT * FROM tb_stock";
$execute = mysqli_query($conn, $order);

$chek = mysqli_affected_rows($conn);

if ($chek > 0) {
    # code...
    $response["code"] = 1;
    $response["messege"] = "Data Already";
    $response["data"] = array();

    while ($takedata = mysqli_fetch_object($execute)) {
        # code...
        $F['id_stock'] = $takedata->id_stock;
        $F['bin'] = $takedata ->bin;
        $F['mm'] = $takedata->mm;
        $F['item'] = $takedata->item;
        $F['available_stock'] = $takedata->available_stock;
        $F['uom'] = $takedata->uom;
        $F['gr_date'] = $takedata->gr_date;

        array_push($response["data"], $F);
    }
} else {
    $response["code"] = 0;
    $response["messege"] = "Data not exisct" ;
}

echo json_encode($response);
mysqli_close($conn);
?>