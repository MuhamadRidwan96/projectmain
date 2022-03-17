		<!-- Begin Page Content -->
        <div class="container-fluid">

<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
  <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
  <a href="<?= base_url('master_location/add_master_location'); ?>" class="d-none d-sm-inline-block btn btn-md btn-primary shadow-sm"></i>ADD DATA</a>
</div>

<!-- row content -->
<div class="row">

  <!-- <?php print_r($allLocation); ?> -->

  <table class="table table-bordered">
    <thead>
      <tr>
        <th scope="col">No.</th>
        <th scope="col">Bin</th>
        <th scope="col">Storage Location</th>
        <th scope="col">Plant</th>
        <th scope="col">Aksi</th>
      </tr>
    </thead>
    <tbody>
      <?php

      foreach ($allLocation as $key => $value) {
        $no = $key+1;
        $bin = $value['bin'];
        echo "<tr>
                <th scope='row'>$no</th>
                <td>".$value['bin']."</td>
                <td>".$value['storage_location']."</td>
                <td>".$value['plant']."</td>
                <td>
                  <a href=".base_url('master_location/view_master_location/'.$bin.'')." class='btn btn-sm btn-info'>View</a>
                  <a href=".base_url('master_location/edit_master_location/'.$bin.'')." class='btn btn-sm btn-warning'>Edit</a>
                  <a href=".base_url('master_location/delete_master_location/'.$bin.'')." class='btn btn-sm btn-danger'>Delete</a>
                </td>
              </tr>";
      }

      ?>
    </tbody>
  </table>

</div>
<!-- end row content -->

</div>
<!-- /.container-fluid -->

</div>
