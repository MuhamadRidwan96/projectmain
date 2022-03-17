    <!-- Begin Page Content -->
    <div class="container-fluid">

      <!-- Page Heading -->
      <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
      </div>

      <!-- row content -->
      <!-- <div class="row"> -->

        <form class="" action="<?= base_url('master_material/proses_add_master_material'); ?>" method="post">
          <div class="form-group row">
            <label for="inputMM" class="col-sm-2 col-form-label">MM</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputMM" name="mm">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputItem" class="col-sm-2 col-form-label">Nama Item</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputItem" name="item">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputColor" class="col-sm-2 col-form-label">Color</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputColor" name="color">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputDescrition" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputDescription" name="description">
            </div>
          </div>
          <button type="submit" name="simpan" class="btn btn-sm btn-primary">Simpan</button>
        </form>

      <!-- </div> -->
      <!-- end row content -->

    </div>
    <!-- /.container-fluid -->

  </div>
