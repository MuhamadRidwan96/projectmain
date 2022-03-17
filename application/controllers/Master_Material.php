<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Master_Material extends CI_Controller
{

	function __construct()
	{
		parent::__construct();
		$this->load->model('Model_master_material', 'master_material');

		if (!$this->session->akses) {
			redirect('auth');
		}
	}

	function index()
	{
		$data = array(
			'title' => "Master Material",
			'allMaterial' => $this->master_material->get_all_material()
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('master_material/index', $data);
		$this->load->view('templates/footer');
	}

	function add_master_material()
	{
		$data = array(
			'title' => "Add Material"
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('master_material/add_master_material', $data);
		$this->load->view('templates/footer');
	}

	function proses_add_master_material()
	{
		if (isset($_POST['simpan'])) {
			$mm = $this->input->post('mm');
			$item = $this->input->post('item');
			$color = $this->input->post('color');
			$description =$this->input->post('description');
			/*
			$quantity = $this->input->post('quantity');
			$uom = $this->input->post('uom');
			$lokasi = $this->input->post('lokasi');
			// untuk men-generate kode
			$mmMaterial = $this->master_material->get_mm_material();
			if ($mmMaterial['mm']) {
				$mmMaterial = $mmMaterial['mm'];
				$substrmmMaterial = substr($mmMaterial, 1);
				$generated = (int)$substrmmMaterial + 1;
				$generatedMM = "M-$generated";
				$mm= $generatedMM;
			} else {
				$mm = "M-1";
			}
			// end untuk men-generate kode
			*/

			// untuk validasi kode yang sudah digunakan
			$getMmMaterial = $this->master_material->get_all_material($mm);

			if ($getMmMaterial) {
				echo "<script>
								alert('mm material sudah digunakan.');
								window.history.back();
							</script>";
				return false;
			}
			// end untuk validasi kode yang sudah digunakan

			$data = array(
				'mm' => $mm,
				'item' => $item,
				'color' => $color,
				'description' =>$description
				/*
				'quantity' => $quantity,
				'uom' => $uom,
				'lokasi' => $lokasi*/
			);

			// print_r($data);
			// exit;

			$this->db->set('mm', FALSE);
			$this->db->insert('tb_material', $data);
			redirect('master_material/index');
		}
	}

	function view_master_material($mm)
	{
		if (!$mm) {
			redirect('master_material/index');
		}

		$data = array(
			'title' => "View Material",
			'viewMaterial' => $this->master_material->get_master_material($mm)
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('master_material/view_master_material', $data);
		$this->load->view('templates/footer');
	}

	function edit_master_material($mm)
	{
		if (!$mm) {
			redirect('master_material/index');
		}

		$data = array(
			'title' => "Edit Material",
			'editMaterial' => $this->master_material->get_master_material($mm)
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('master_material/edit_master_material', $data);
		$this->load->view('templates/footer');
	}

	function proses_edit_master_material($mm)
	{
		if (isset($_POST['simpan'])) {
			$mm = $this->input->post('mm');
			$item = $this->input->post('item');
			$color = $this->input->post('color');
			$description = $this->input->post('description');
			/*
			$quantity = $this->input->post('quantity');
			$uom = $this->input->post('uom');
			$lokasi = $this->input->post('lokasi');*/

			$data = array(
				'mm' => $mm,
				'item' => $item,
				'color' => $color,
				'description' => $description
				/*
				'quantity' => $quantity,
				'uom' => $uom,
				'lokasi' => $lokasi*/
			);

			// print_r($data);
			// exit;

			$this->db->where('mm', $mm);
			$this->db->update('tb_material', $data);
			redirect('master_material/index');
		}
	}

	function delete_master_material($mm)
	{
		$this->db->delete('tb_material', array('mm' => $mm));
		redirect('master_material/index');
	}

	function get_material($search = null)
	{
		$data = $this->master_material->get_material($search);
		if ($data) {
			echo json_encode($data);
		} else {
			echo "DATA TIDAK DITEMUKAN";
		}
	}
}
