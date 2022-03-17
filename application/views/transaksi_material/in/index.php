		<!-- Begin Page Content -->
    <div class="container-fluid">

      <!-- Page Heading -->
      <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
        <a href="<?= base_url('transaksi_material/add_material_in'); ?>" class="d-none d-sm-inline-block btn btn-md btn-primary shadow-sm"></i>ADD DATA</a>
      </div>

      <!-- row content -->
      <div class="row">

        <!-- <?php print_r($materialIn); ?> -->

        <table class="table table-bordered">
          <thead>
            <tr style="font-size:10">
              <th scope="col">No.</th>
              <th scope="col">No Doc</th>
              <th scope="col">Id Stok</th>
              <th scope="col">Material</th>
              <th scope="col">Quantity</th>
              <th scope="col">Uom</th>
              <th scope="col">Date</th>
              <th scope="col">Shift</th>
              <th scope="col">Nik</th>
              <th scope="col">Aksi</th>
            </tr>
          </thead>
          <tbody>
            <?php

            foreach ($materialIn as $key => $value) {
              $no = $key+1;
              $no_doc = $value['no_doc'];
              echo "<tr>
                      <th scope='row'>$no</th>
                      <td>".$value['no_doc']."</td>
                      <td>".$value['id_stock']."</td>
                      <td>".$value['material']."</td>
                      <td>".$value['quantity']."</td>
                      <td>".$value['uom']."</td>
                      <td>".date($value['date'])."</td>
                      <td>".$value['shift']."</td>
                      <td>".$value['nik']."</td>
                      <td>
                        <a href=".base_url('transaksi_material/view_material_in/'.$no_doc.'')." class='btn btn-sm btn-info'>View</a>
                        <a href=".base_url('transaksi_material/edit_material_in/'.$no_doc.'')." class='btn btn-sm btn-warning'>Edit</a>
                        <a href=".base_url('transaksi_material/delete_material_in/'.$no_doc.'')." class='btn btn-sm btn-danger'>Delete</a>
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
