<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
  </div>

  <!-- <?php print_r($viewStocks); ?> -->

  <!-- row content -->
  <!-- <div class="row"> -->

      <div class="form-group row">
        <label for="inputId_stock" class="col-sm-2 col-form-label">Id Stocks</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputId_stock" readonly="readonly" name="id_stock" value="<?= $viewStocks['id_stock']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputBin" class="col-sm-2 col-form-label">Bin</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputBin" name="bin" readonly="readonly" value="<?= $viewStocks['bin']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputMm" class="col-sm-2 col-form-label">MM</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputMm" name="mm" readonly="readonly" value="<?= $viewStocks['mm']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputItem" class="col-sm-2 col-form-label">Nama Item</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputItem" name="item" readonly="readonly" value="<?= $viewStocks['item']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputAvailable_stock" class="col-sm-2 col-form-label">Available Stocks</label>
        <div class="col-sm-10">
          <input type="number" class="form-control" id="inputAvailable_stock" name="available_stock" readonly="readonly" value="<?= $viewStocks['available_stock']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputUom" class="col-sm-2 col-form-label">UOM</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputUom" name="uom" readonly="readonly" value="<?= $viewStocks['uom']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputGr_date" class="col-sm-2 col-form-label">GR Date</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputGr_date" name="gr_date" readonly="readonly" value="<?= $viewStocks['gr_date']; ?>">
        </div>
      </div>

  <!-- </div> -->
  <!-- end row content -->

</div>
<!-- /.container-fluid -->

</div>
