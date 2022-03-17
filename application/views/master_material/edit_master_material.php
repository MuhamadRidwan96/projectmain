    <!-- Begin Page Content -->
    <div class="container-fluid">

      <!-- Page Heading -->
      <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
      </div>
      <hr>

      <!-- row content -->
      <!-- <div class="row"> -->

        <form class="formEdit" action="<?= base_url('master_material/proses_edit_master_material/'.$editMaterial['mm']); ?>" method="post">
          <div class="form-group row">
            <label for="inputMm" class="col-sm-2 col-form-label">MM</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="iinputMm" name="mm" value="<?= $editMaterial['mm']; ?>">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputItem" class="col-sm-2 col-form-label">Nama Item</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputItem" name="item" value="<?= $editMaterial['item']; ?>">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputColor" class="col-sm-2 col-form-label">Color</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputColor" name="color" value="<?= $editMaterial['color']; ?>">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputDescription" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inpuDescription" name="description" value="<?= $editMaterial['description']; ?>">
            </div>
          </div>
          <button type="submit" name="simpan" class="btn btn-sm btn-primary">Simpan</button>
        </form>

      <!-- </div> -->
      <!-- end row content -->

    </div>
    <!-- /.container-fluid -->

  </div>
