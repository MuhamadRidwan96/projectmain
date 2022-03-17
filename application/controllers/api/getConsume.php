<?php

require_once'connection_db.php';

$order = "SELECT * FROM tb_consumption";
$execute = mysqli_query($conn, $order);

$chek = mysqli_affected_rows($conn);

if ($chek > 0) {
    # code...
    $response["code"] = 1;
    $response["messege"] = "Data Already";
    $response["data"] = array();

    while ($takedata = mysqli_fetch_object($execute)) {
        # code...

        $F['mat_doc'] = $takedata->mat_doc;
        $F['id_stock'] = $takedata->id_stock;
        $F['material'] = $takedata->material;
        $F['quantity'] = $takedata->quantity;
        $F['uom'] = $takedata->uom;
        $F['date'] = $takedata->date;
        $F['shift'] = $takedata->shift;
        $F['nik'] = $takedata->nik;
        
        
        
        array_push($response["data"], $F);
    }
} else {
    $response["code"] = 0;
    $response["messege"] = "Data not exisct";
}

echo json_encode($response);
mysqli_close($conn);
?>