<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
  </div>

  <!-- <?php print_r($viewMaterial); ?> -->

  <!-- row content -->
  <!-- <div class="row"> -->

  <div class="form-group row">
        <label for="inputMat_doc" class="col-sm-2 col-form-label">Mat Doc</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputMat_doc" readonly="readonly" name="mat_doc" value="<?= $viewMaterial['mat_doc']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputId_stock" class="col-sm-2 col-form-label">Id Stock</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputId_stock" readonly="readonly" name="id_stock" value="<?= $viewMaterial['id_stock']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputMaterial" class="col-sm-2 col-form-label">Item</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputMaterial" name="material" readonly="readonly" value="<?= $viewMaterial['material']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputQuantity" class="col-sm-2 col-form-label">Quantity</label>
        <div class="col-sm-10">
          <input type="number" class="form-control" id="inputQuantity" name="quantity" readonly="readonly" value="<?= $viewMaterial['quantity']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputUom" class="col-sm-2 col-form-label">Uom</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputUom" name="uom" readonly="readonly" value="<?= $viewMaterial['uom']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputDate" class="col-sm-2 col-form-label">Date</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputDate" name="date" readonly="readonly" value="<?= date($viewMaterial['date']); ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputShift" class="col-sm-2 col-form-label">Shift</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" readonly="readonly" id="inputShift" name="shift" value="<?= $viewMaterial['shift']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputNik" class="col-sm-2 col-form-label">NIK</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" readonly="readonly" id="inputNik" name="nik" value="<?= $viewMaterial['nik']; ?>">
        </div>
      </div>

  <!-- </div> -->
  <!-- end row content -->

</div>
<!-- /.container-fluid -->

</div>
