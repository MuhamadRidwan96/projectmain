		<!-- Begin Page Content -->
		<div class="container-fluid">

			<!-- Page Heading -->
			<div class="d-sm-flex align-items-center justify-content-between mb-4">
				<h1 class="h3 mb-0 text-gray-800"><?= $title; ?></h1>
				<a href="<?= base_url('sysconfig/add_user'); ?>" class="d-none d-sm-inline-block btn btn-md btn-primary shadow-sm"></i>ADD DATA</a> 
			</div>

			<!-- row content -->
			<div class="row">

			       <!-- <?php print_r($allUser); ?> -->

				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">No.</th>
							<th scope ="col">Nik</th>
							<th scope="col">Username</th>
							<th scope="col">Password</th>
							<th scope="col">Full Name</th>
							<th scope="col">Phone</th>
							<th scope="col">Email</th>
							<th scope='col'>Aksi</th>
						</tr>
					</thead>
					<tbody>
						<?php
						foreach ($allUser as $key => $value) {
							$no = $key + 1;
							$nik = $value['nik'];
							echo "<tr>
									<th scope='row'>$no</th>
									<td>".$value['nik']."</td>
									<td>".$value['username']."</td>
									<td>".$value['password']."</td>
                                    <td>" .$value['full_name']. "</td>
                                    <td>" .$value['phone']."</td>
									<td>" .$value['email']."</td>	
                                    <td>
                                      <a href=" . base_url('sysconfig/edit_user/' . $nik. '') . " class='btn btn-sm btn-warning'>Edit</a>
                                      <a href=" . base_url('sysconfig/delete_user/' . $nik . '') . " class='btn btn-sm btn-danger'>Delete</a>
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