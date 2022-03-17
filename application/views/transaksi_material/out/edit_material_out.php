<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
  </div>

  <!-- <?php print_r($editMaterial); ?> -->

  <!-- row content -->
  <!-- <div class="row"> -->

    <form class="" action="<?= base_url('transaksi_material/proses_edit_material_out/'.$editMaterial['mat_doc']); ?>" method="post">
      <div class="form-group row">
        <label for="inputMat_doc" class="col-sm-2 col-form-label">Mat-Doc</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="inputMat_doc" readonly="readonly" name="mat_doc" value="<?= $editMaterial['mat_doc']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputId_stock" class="col-sm-2 col-form-label">ID Stock</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="inputId_stock" name="id_stock" readonly="readonly" value="<?= $editMaterial['id_stock']; ?>">
        </div>
             <!-- Button modal -->
             <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal1" style="width: 70px; height: 34px;">
          ....
        </button>
      </div>
      <div class="form-group row">
        <label for="inputMaterial" class="col-sm-2 col-form-label">Material</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputMaterial" name="material" readonly="readonly" value="<?= $editMaterial['material']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputQuantity" class="col-sm-2 col-form-label">Quantity</label>
        <div class="col-sm-10">
          <input type="number" class="form-control" id="inputQuantity" name="quantity" value="<?= $editMaterial['quantity']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputUom" class="col-sm-2 col-form-label">UOM</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputUom" name="uom" value="<?= $editMaterial['uom']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputDate" class="col-sm-2 col-form-label">Date</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputDate" name="date" readonly="readonly" value="<?= date($editMaterial['date']); ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputShift" class="col-sm-2 col-form-label">Shift</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputShift" name="shift" value="<?= $editMaterial['shift']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputNik" class="col-sm-2 col-form-label">Nik</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="inputNik" name="nik" readonly="readonly" value="<?= $editMaterial['nik']; ?>">
        </div>
             <!-- Button modal -->
             <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal2" style="width: 70px; height: 34px;">
          ....
        </button>
      </div>
      <button type="submit" name="simpan" class="btn btn-sm btn-primary">Simpan</button>
    </form>

  <!-- </div> -->
  <!-- end row content -->

</div>
<!-- /.container-fluid -->

</div>


<!-- Modal -->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel2">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <table class="table table-hovered">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">NIK</th>
            </tr>
          </thead>
          <tbody>
            <?php foreach ($allUsers as $key => $value) { ?>
              <tr onclick="getData2(<?= $key; ?>)" style="cursor: pointer">
                <th scope="row"><?= $key+1; ?>.</th>
                <td id="nik<?= $key; ?>" value="<?= $value['nik']; ?>"><?= $value['nik']; ?></td>
                <td id="full_name<?= $key; ?>" value="<?= $value['full_name']; ?>"><?= $value['full_name']; ?></td>
              </tr>
            <?php } ?>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
function getData2(row)
{
  var nik = document.getElementById("nik"+row).innerHTML;

  document.getElementById("inputNik").value = nik;

  $('#exampleModal').modal('hide');
}
</script>

<!-- Modal -->
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel1">Stock</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <table class="table table-hovered">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">ID Stock</th>
              <th scope="col">Material</th>
            </tr>
          </thead>
          <tbody>
            <?php foreach ($allStocks as $key => $value) { ?>
              <tr onclick="getData1(<?= $key; ?>)" style="cursor: pointer">
                <th scope="row"><?= $key+1; ?>.</th>
                <td id="id_stock<?= $key; ?>" value="<?= $value['id_stock']; ?>"><?= $value['id_stock']; ?></td>
                <td id="item<?= $key; ?>" value="<?= $value['item']; ?>"><?= $value['item']; ?></td>
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
  var id_stock = document.getElementById("id_stock"+row).innerHTML;
  var item =  document.getElementById("item"+row).innerHTML;

  document.getElementById("inputId_stock").value = id_stock;
  document.getElementById("inputMaterial").value = item;

  $('#exampleModal1').modal('hide');
}
</script>

<!-- Modal -->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel2">Stock</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <table class="table table-hovered">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">NIK</th>
            </tr>
          </thead>
          <tbody>
            <?php foreach ($allUsers as $key => $value) { ?>
              <tr onclick="getData2(<?= $key; ?>)" style="cursor: pointer">
                <th scope="row"><?= $key+1; ?>.</th>
                <td id="nik<?= $key; ?>" value="<?= $value['nik']; ?>"><?= $value['nik']; ?></td>
              </tr>
            <?php } ?>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
function getData2(row)
{
  var nik = document.getElementById("nik"+row).innerHTML;

  document.getElementById("inputNik").value = nik;

  $('#exampleModal2').modal('hide');
}
</script>