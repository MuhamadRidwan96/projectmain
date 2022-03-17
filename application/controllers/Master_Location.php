<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Master_Location extends CI_Controller
{

	function __construct()
	{
		parent::__construct();
		$this->load->model('Model_master_location','master_location');
		$this->load->model('Model_master_material', 'master_material');

		if (!$this->session->akses) {
			redirect('auth');
		}
	}

	function index()
	{
		$data = array(
			'title' => "Master Location",
			'allLocation' => $this->master_location->get_all_location()
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('master_location/index', $data);
		$this->load->view('templates/footer');
	}

	function add_master_location()
	{
		$data = array(
			'title' => "Add Location",
			'allLocation' => $this->master_material->get_all_material(),
			'all' =>$this->master_location->get_all_location()
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('master_location/add_master_location', $data);
		$this->load->view('templates/footer');
	}

	function proses_add_master_location()
	{
		if (isset($_POST['simpan'])) {
            $bin =$this->input->post('bin');
			$storage_location = $this->input->post('storage_location');
			$plant= $this->input->post('plant');
			$quantity = $this->input->post('quantity');
			$uom = $this->input->post('uom');
			$lokasi = $this->input->post('lokasi');

            // untuk men-generate kode
           /*
			$mBin = $this->master_material->get_mm_material();
			if ($mBin['bin']) {
				$mBin = $mBin['bin'];
				$substrmBin = substr($mBin, 1);
				$generated = (int)$substrmBin + 1;
				$generatedBin = "M-$generated";
				$bin= $generatedBin;
			} else {
				$bin = "M-1";
			}
			// end untuk men-generate kode

			// untuk validasi kode yang sudah digunakan
			$getMbin = $this->master_location->get_all_location($bin);

			if ($getMbin) {
				echo "<script>
								alert('location already exicst.');
								window.history.back();
							</script>";
				return false;
			}
			// end untuk validasi kode yang sudah digunakan
				 */
			$data = array(
                'bin' => $bin,
				'storage_location' => $storage_location,
				'plant' => $plant
				/*
				'quantity' => $quantity,
				'uom' => $uom,
				'lokasi' => $lokasi*/
			);

			// print_r($data);
			// exit;

			$this->db->set('bin', FALSE);
            $this->db->insert('tb_location', $data);
			redirect('master_location/index');
		}
	}

	function view_master_location($bin)
	{
		if (!$bin) {
			redirect('master_location/index');
		}

		$data = array(
			'title' => "View Location",
			'viewLocation' => $this->master_location->get_master_location($bin));
		$this->load->view('templates/header', $data);
        $this->load->view('templates/sidebar', $data);
		$this->load->view('master_location/view_master_location', $data);
		$this->load->view('templates/footer');
	}

	function edit_master_location($bin)
	{
		if (!$bin) {
			redirect('master_location/index');
		}

		$data = array(
			'title' => "Edit Location",
			'allLocation' => $this->master_material->get_all_material(),
			'editLocation' => $this->master_location->get_master_location($bin));
		$this->load->view('templates/header', $data);
        $this->load->view('templates/sidebar', $data);
		$this->load->view('master_location/edit_master_location',$data);
		$this->load->view('templates/footer');
	}

	function proses_edit_master_location($bin)
	{
		if (isset($_POST['simpan'])) {
            $bin = $this->input->post('bin');
			$storage_location= $this->input->post('storage_location');
			$plant = $this->input->post('plant');

			$data = array(
				'bin' => $bin,
				'storage_location' => $storage_location,
				'plant' => $plant
			);

			// print_r($data);
			// exit;

			$this->db->where('bin', $bin);
			$this->db->update('tb_location', $data);
			redirect('master_location/index');
		}
	}

	function delete_master_location($bin)
	{
		$this->db->delete('tb_location', array('bin' => $bin));
		redirect('master_location/index');
	}

	function get_location($search = null)
	{
		$data = $this->master_location->get_location($search);
		if ($data) {
			echo json_encode($data);
		} else {
			echo "DATA TIDAK DITEMUKAN";
		}
	}
}
