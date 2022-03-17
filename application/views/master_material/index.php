		<!-- Begin Page Content -->
    <div class="container-fluid">

      <!-- Page Heading -->
      <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
        <a href="<?= base_url('master_material/add_master_material'); ?>" class="d-none d-sm-inline-block btn btn-md btn-primary shadow-sm"></i>ADD DATA</a>
      </div>

      <!-- row content -->
      <div class="row">

        <!-- <?php print_r($allMaterial); ?> -->

        <table class="table table-bordered">
          <thead>
            <tr>
              <th scope="col">No.</th>
              <th scope="col">MM</th>
              <th scope="col">Item</th>
              <th scope="col">Color</th>
              <th scope ="col">Description</th>
              <th scope="col">Aksi</th>
            </tr>
          </thead>
          <tbody>
            <?php

            foreach ($allMaterial as $key => $value) {
              $no = $key+1;
              $mm = $value['mm'];
              echo "<tr>
                      <th scope='row'>$no</th>
                      <td>".$value['mm']."</td>
                      <td>".$value['item']."</td>
                      <td>".$value['color']."</td>
                      <td>".$value['description']."</td>
                      <td>
                        <a href=".base_url('master_material/view_master_material/'.$mm.'')." class='btn btn-sm btn-info'>View</a>
                        <a href=".base_url('master_material/edit_master_material/'.$mm.'')." class='btn btn-sm btn-warning'>Edit</a>
                        <a href=".base_url('master_material/delete_master_material/'.$mm.'')." class='btn btn-sm btn-danger'>Delete</a>
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
