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
        <label for="inputNo_doc" class="col-sm-2 col-form-label">No Doc</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputNo_doc" readonly="readonly" name="no_doc" value="<?= $viewMaterial['no_doc']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputId_stock" class="col-sm-2 col-form-label">Id Stock</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" readonly="readonly" id="inputId_stock" name="id_stock" value="<?= $viewMaterial['id_stock']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputMaterial" class="col-sm-2 col-form-label">Material</label>
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
        <label for="inputUom" class="col-sm-2 col-form-label">UOM</label>
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
        <label for="inputShift" class="col-sm-2 col-form-label">Text</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputShift" name="shift" readonly="readonly" value="<?= $viewMaterial['shift']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputNik" class="col-sm-2 col-form-label">NIK</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputNik" name="nik" readonly="readonly" value="<?= $viewMaterial['nik']; ?>">
        </div>
      </div>

  <!-- </div> -->
  <!-- end row content -->

</div>
<!-- /.container-fluid -->

</div>
