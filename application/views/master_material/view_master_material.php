    <!-- Begin Page Content -->
    <div class="container-fluid">

      <!-- Page Heading -->
      <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
      </div>
      <hr>

      <!-- row content -->
      <!-- <div class="row"> -->

          <div class="form-group row">
            <label for="inputMm" class="col-sm-2 col-form-label">MM</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputMm" readonly="readonly" name="mm" value="<?= $viewMaterial['mm']; ?>">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputItem" class="col-sm-2 col-form-label">Nama Item</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputItem" readonly="readonly" name="item" value="<?= $viewMaterial['item']; ?>">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputColor" class="col-sm-2 col-form-label">Color</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inputColor" readonly="readonly" name="color" value="<?= $viewMaterial['color']; ?>">
            </div>
          </div>
          <div class="form-group row">
            <label for="inputDescription" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="inpuDescription" readonly ="readonly" name="description" value="<?= $viewMaterial['description']; ?>">
            </div>
          </div>
      <!-- </div> -->
      <!-- end row content -->

    </div>
    <!-- /.container-fluid -->

  </div>
