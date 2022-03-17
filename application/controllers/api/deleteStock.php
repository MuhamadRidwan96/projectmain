<?PHP

require_once'connection_db.php';

$response = array();


if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $id_stock  = $_POST['id_stock'];


    $perintah = "DELETE FROM tb_stock WHERE id_stock = '$id_stock'";
    $eksekusi = mysqli_query($conn, $perintah);
    $cek      = mysqli_affected_rows($conn);

    if ($cek > 0) {

        $response["code"]  = 1;
        $response["messege"] = "Data berhasil dihapus";
    } else {

        $response["code"] = 0;
        $response["messege"] = "Data gagal dihapus";
    }
} else {
    $response["code"] = 0;
    $response["messege"] = "TIDAK ADA POST DATA";
}

echo json_encode($response);
mysqli_close($conn);
