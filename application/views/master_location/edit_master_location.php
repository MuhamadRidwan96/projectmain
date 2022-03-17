<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
  </div>

  <!-- <?php print_r($editLocation); ?> -->

  <!-- row content -->
  <!-- <div class="row"> -->

    <form class="" action="<?= base_url('master_location/proses_edit_master_location/'.$editLocation['bin']); ?>" method="post">
      <div class="form-group row">
        <label for="inputBin" class="col-sm-2 col-form-label">Bin</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputBin" name="bin" value="<?= $editLocation['bin']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputStorage_Location" class="col-sm-2 col-form-label">Stotrage Location</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="inputStorage_Location" name="storage_location" value="<?= $editLocation['storage_location']; ?>">
        </div>
      </div>
      <div class="form-group row">
        <label for="inputPlant" class="col-sm-2 col-form-label">Plant</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputPlant" name="plant" value="<?= $editLocation['plant']; ?>">
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
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
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
            <?php foreach ($allLocation as $key => $value) { ?>
              <tr onclick="getData(<?= $key; ?>)" style="cursor: pointer">
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
  var color = document.getElementById("color"+row).innerHTML;

  document.getElementById("inputMm").value = mm;
  document.getElementById("inputItem").value = item;
  document.getElementById("inputColor").value = color;
 

  $('#exampleModal').modal('hide');
}
</script>
