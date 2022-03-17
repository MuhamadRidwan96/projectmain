<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
  </div>

  <!-- <?php print_r($viewLocation); ?> -->

  <!-- row content -->
  <!-- <div class="row"> -->

      <div class="form-group row">
        <label for="inputBin" class="col-sm-2 col-form-label">Bin</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputBin" readonly="readonly" name="bin" value="<?= $viewLocation['bin']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputStorage_location" class="col-sm-2 col-form-label">Storage Location</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputStorage_location" name="storage_location" readonly="readonly" value="<?= $viewLocation['storage_location']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputPlant" class="col-sm-2 col-form-label">Nama Item</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputPlant" name="plant" readonly="readonly" value="<?= $viewLocation['plant']; ?>">
        </div>
      </div>

  <!-- </div> -->
  <!-- end row content -->

</div>
<!-- /.container-fluid -->

</div>
