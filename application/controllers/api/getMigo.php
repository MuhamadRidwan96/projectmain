<?php

require_once'connection_db.php';

if(isset($_GET['key'])){
    $key = $_GET["key"];
    if($type=='tb_migo'){
        $order ="SELECT * FROM tb_migo WHERE no_doc LIKE '%$key%'";
        $execute = mysql_query($conn,$order);
        $chek = mysqli_affected_rows($conn);

        if($chek>0){
            $response["data"] = array();

            while($takedata = mysqli_fetch_object($execute)){
                $F['no_doc'] = $takedata->no_doc;
                $F['id_stock'] = $takedata->id_stock;
                $F['mm'] = $takedata->mm;
                $F['item'] = $takedata->item;
                $F['quantity'] = $takedata->quantity;
                $F['uom'] = $takedata->uom;
                $F['good_recipient'] = $takedata->good_recipient;
                $F['date'] = $takedata->date;
                $f['shift'] = $takedata->shift;
                array_push($response["data"],$F);
            } 
        } else {
                
            $response ["message"]= "DATA NOT EXIST";
        }

    }
    echo json_encode($response);

} else {
    # code...

$order = "SELECT * FROM tb_migo";
$execute = mysqli_query($conn, $order);

$chek = mysqli_affected_rows($conn);

if ($chek > 0) {
    # code...
    $response["code"] = 1;
    $response["messege"] = "Data Already";
    $response["data"] = array();

    while ($takedata = mysqli_fetch_object($execute)) {
        # code...

        $F['no_doc'] = $takedata->no_doc;
        $F['id_stock'] = $takedata->id_stock;
        $F['material'] = $takedata->material;
        $F['quantity'] = $takedata->quantity;
        $F['uom'] = $takedata->uom;
        $F['date'] = $takedata->date;
        $F['shift'] = $takedata->shift;
        $F['nik'] = $takedata->nik;

        array_push($response["data"],$F);
    }
} else {
    $response["code"] = 0;
    $response["messege"] = "Data not exisct";
}

echo json_encode($response);
mysqli_close($conn);
}
?>