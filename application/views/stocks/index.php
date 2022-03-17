		<!-- Begin Page Content -->
        <div class="container-fluid">

<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
  <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
  <a href="<?= base_url('stocks/add_stocks'); ?>" class="d-none d-sm-inline-block btn btn-md btn-primary shadow-sm"></i>ADD DATA</a>
</div>

<!-- row content -->
<div class="row">

  <!-- <?php print_r($allStocks); ?> -->

  <table class="table table-bordered">
    <thead>
      <tr>
        <th scope="col">No.</th>
        <th scope="col">Id Stock</th>
        <th scope="col">Bin</th>
        <th scope="col">MM</th>
        <th scope="col">Item</th>
        <th scope="col">Stock</th>
        <th scope="col">Uom</th>
        <th scope="col">GR Date</th>
        <th scope="col">Aksi</th>
      </tr>
    </thead>
    <tbody>
      <?php

      foreach ($allStocks as $key => $value) {
        $no = $key+1;
        $id_stock = $value['id_stock'];
        echo "<tr>
                <th scope='row'>$no</th>
                <td>".$value['id_stock']."</td>
                <td>".$value['bin']."</td>
                <td>".$value['mm']."</td>
                <td>".$value['item']."</td>
                <td>".$value['available_stock']."</td>
                <td>".$value['uom']."</td>
                <td>".$value['gr_date']."</td>
                <td>
                  <a href=".base_url('stocks/view_stocks/'.$id_stock.'')." class='btn btn-sm btn-info'>View</a>
                  <a href=".base_url('stocks/edit_stocks/'.$id_stock.'')." class='btn btn-sm btn-warning'>Edit</a>
                  <a href=".base_url('stocks/delete_stocks/'.$id_stock.'')." class='btn btn-sm btn-danger'>Delete</a>
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
