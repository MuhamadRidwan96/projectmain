<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
  </div>

  <!-- <?php print_r($editStocks); ?> -->

  <!-- row content -->
  <!-- <div class="row"> -->

    <form class="" action="<?= base_url('stocks/proses_edit_stocks/'.$editStocks['id_stock']); ?>" method="post">
      <div class="form-group row">
        <label for="inputId_stock" class="col-sm-2 col-form-label">Id Stock</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputId_stock" readonly="readonly" name="id_stock" value="<?= $editStocks['id_stock']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputBin" class="col-sm-2 col-form-label">BIN</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" readonly="readonly" id="inputBin" name="bin" value="<?= $editStocks['bin']; ?>">
        </div>
        <!-- Button modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal1" style="width: 80px; height: 34px;">
          ....
        </button>
      </div>
      <div class="form-group row">
        <label for="inputMm" class="col-sm-2 col-form-label">MM</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" readonly="readonly" id="inputMm" name="mm" value="<?= $editStocks['mm']; ?>">
        </div>
        <!-- Button modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal2" style="width: 80px; height: 34px;">
          ....
        </button>
      </div>
      <div class="form-group row">
        <label for="inputItem" class="col-sm-2 col-form-label">Item Name</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputItem" readonly="readonly" name="item" value="<?= $editStocks['item']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputinputAvailable_stock" class="col-sm-2 col-form-label">Available Stocks</label>
        <div class="col-sm-10">
          <input type="number" class="form-control" id="inputAvailable_stock" name="available_stock" value="<?= $editStocks['available_stock']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputUom" class="col-sm-2 col-form-label">Uom</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputUom" name="uom" value="<?= $editStocks['uom']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputgR_date" class="col-sm-2 col-form-label">Gr Date</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputGr_date" name="gr_date" readonly="readonly" value="<?= date("Y-m-d"); ?>">
        </div>
      </div>
      <button type="submit" name="simpan" class="btn btn-sm btn-primary">Simpan</button>
    </form>

  <!-- </div> -->
  <!-- end row content -->

</div>
<!-- /.container-fluid -->

</div>



<!-- Modal -->
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel1">Bin</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <table class="table table-hovered">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Bin</th>
            </tr>
          </thead>
          <tbody>
            <?php foreach ($allLocation as $key => $value) { ?>
              <tr onclick="getData1(<?= $key; ?>)" style="cursor:pointer">
                <th scope="row"><?= $key+1; ?>.</th>
                <td id="bin<?= $key; ?>" value="<?= $value['bin']; ?>"><?= $value['bin']; ?></td>
              </tr>
            <?php } ?>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
function getData1(row)
{
  var bin = document.getElementById("bin"+row).innerHTML;
  document.getElementById("inputBin").value = bin;

  $('#exampleModal1').modal('hide');
}
</script>



<!-- Modal -->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel2">Material</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <table class="table table-hovered">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">MM</th>
              <th scope="col">Item</th>
              <th scope="col">Color</th>
            </tr>
          </thead>
          <tbody>
            <?php foreach ($allMaterial as $key => $value) { ?>
              <tr onclick="getData(<?= $key; ?>)" style="cursor:pointer">
                <th scope="row"><?= $key+1; ?>.</th>
                <td id="mm<?= $key; ?>" value="<?= $value['mm']; ?>"><?= $value['mm']; ?></td>
                <td id="item<?= $key; ?>" value="<?= $value['item']; ?>"><?= $value['item']; ?></td>
                <td id="color<?= $key; ?>" value="<?= $value['color']; ?>"><?= $value['color']; ?></td>
              </tr>
            <?php } ?>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
function getData(row)
{
  var mm = document.getElementById("mm"+row).innerHTML;
  var item = document.getElementById("item"+row).innerHTML;
  document.getElementById("inputMm").value = mm;
  document.getElementById("inputItem").value = item;
  $('#exampleModal2').modal('hide');
}
</script>
