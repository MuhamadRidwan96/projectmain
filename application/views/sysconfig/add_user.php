    <!-- Begin Page Content -->
    <div class="container-fluid">

      <!-- Page Heading -->
      <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
      </div>

      <!-- row content -->
      <!-- <div class="row"> -->
      <form class="" action="<?= base_url('sysconfig/proses_add_user'); ?>" method="post">
          <div class="form-group row">
            <label for="inputNik" class="col-sm-2 col-form-label">Nik</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputNik" name="nik">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputUsername" class="col-sm-2 col-form-label">Username</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputUsername" name="username">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputPassword" name="password">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputFull_Name" class="col-sm-2 col-form-label">Full Name</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputFull_Name" name="full_name">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputPhone" class="col-sm-2 col-form-label">Phone</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputPhone" name="phone">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputEmail" name="email">
            </div>
          </div>
          <button type="submit" name="simpan" class="btn btn-sm btn-primary">Simpan</button>
        </form>

      <!-- </div> -->
      <!-- end row content -->

    </div>
    <!-- /.container-fluid -->

  </div>
