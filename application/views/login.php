<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><?= $title; ?></title>
    <!-- Bootstrap CSS -->
    <link href="<?= base_url('assets/'); ?>css/bootstrap.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="<?= base_url('assets/'); ?>vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        /*form login*/
        body {
            height: 610px;
            background-color: #f2f2f2;
        }

        form {
            border-radius: 6px;
            box-shadow: 0px 1px 10px 1px;
        }

        h2 {
            font-weight: 600;
            color: black;
        }

        .row {
            margin-right: 0px;
            margin-left: 0px;
        }

        /*end form login*/
    </style>
</head>

<body>
    <div class="container-fluid">

        <div class="row pt-5 pb-5 justify-content-center">
            <form action="" method="post" class="col-xs-3 col-sm-6 col-md-4 col-lg-3 border pt-2 pb-3 mr-2 ml-2 text-center">
                <h2>FORM LOGIN</h2>
                <hr>
                <div class="form-group mr-2 ml-2">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="fas fa-fw fa-at"></i></div>
                        </div>
                        <input type="text" class="form-control" name="username" id="username" autofocus placeholder="Masukan Username">
                    </div>
                </div>
                <div class="form-group mr-2 ml-2">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="fas fa-fw fa-lock"></i></div>
                        </div>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Masukan Password" autocomplete="off">
                    </div>
                </div>
                <button type="submit" name="login" class="btn btn-primary">Masuk</button>
                <!-- <a href="javascript:window.history.go(-1);" class="btn btn-danger">Batal</a> -->
                <button type="reset" class="btn btn-danger">Reset</button>
                <!-- <small>
                    <p class="text-muted mt-3">Belum memiliki akun?? <a href="<?= base_url('auth/registration'); ?>">Register</a> sekarang</p>
                </small> -->
            </form>
        </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Bootstrap JS -->
    <script src="<?= base_url('assets/'); ?>js/jquery-3.3.1.min.js"></script>
    <script src="<?= base_url('assets/'); ?>js/bootstrap.js"></script>
</body>

</html>