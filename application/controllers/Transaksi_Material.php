<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Transaksi_Material extends CI_Controller
{

	function __construct()
	{
		parent::__construct();
		$this->load->model('Model_transaksi_material', 'transaksi_material');
		$this->load->model('Model_master_material', 'master_material');
		$this->load->model('Model_master_stocks','stocks');
		$this->load->model('Users_model','sysconfig');

		if (!$this->session->akses) {
			redirect('auth');
		}
	}

	// start controller material in
	function material_in($search = null)
	{
		$data = array(
			'title' => "Goods Issue",
			'materialIn' => $this->transaksi_material->get_material_in($search)
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('transaksi_material/in/index', $data);
		$this->load->view('templates/footer');
	}

	function add_material_in()
	{
		$data = array(
			'title' => "Add Material IN",
			'allMaterial' => $this->master_material->get_all_material(),
			'allStocks' => $this->stocks->get_all_stocks(),
			'allUsers' => $this->sysconfig->get_all_user()
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('transaksi_material/in/add_material_in', $data);
		$this->load->view('templates/footer');
	}

	function proses_add_material_in()
	{
		if (isset($_POST['simpan'])) {
			$no_doc = $this->input->post('no_doc');
			$id_stock =$this->input->post('id_stock');
			$material = $this->input->post('material');
			$quantity = $this->input->post('quantity');
			$uom = $this->input->post('uom');
			$date = $this->input->post('date');
			$shift = $this->input->post('shift');
			$nik = $this->input->post('nik');

			// untuk men-generate kode
			/*
			$mmMaterial = $this->transaksi_material->get_kode_material_in();
			if ($kodeMaterial['kode_material_in']) {
				$kodeMaterial = $kodeMaterial['kode_material_in'];
				$substrKodeMaterial = substr($kodeMaterial, 1);
				$generated = (int)$substrKodeMaterial + 1;
				$generatedKode = "MI-$generated";
				$kode = $generatedKode;
			} else {
				$kode = "MI-1";
			}
			// end untuk men-generate kode

			// untuk validasi kode yang sudah digunakan
			$getKodeMaterial = $this->transaksi_material->get_material_in($kode);

			if ($getKodeMaterial) {
				echo "<script>
						alert('kode material in sudah digunakan.');
						window.history.back();
					  </script>";
				return false;
			}*/
			// end untuk validasi kode yang sudah digunakan

			$data = array(
				'no_doc' =>$no_doc,
				'id_stock' => $id_stock,
				'material' => $material,
				'quantity' => $quantity,
				'uom' => $uom,
				'date' =>$date,
				'shift' => $shift,
				'nik' => $nik
				
			);

			$this->db->set('no_doc', FALSE);
			$this->db->insert('tb_migo', $data);
			redirect('transaksi_material/material_in');
		}
	}

	function edit_material_in($no_doc)
	{
		if ($no_doc== null) {
			redirect('transaksi_material/material_in');
		}

		$data = array(
			'title' => "Edit Material IN",
			'allMaterial' => $this->master_material->get_all_material(),
			'allStocks' => $this->stocks->get_all_stocks(),
			'editMaterial' => $this->transaksi_material->get_transaksi_material_in($no_doc),
			'allUsers' => $this->sysconfig->get_all_user()

		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('transaksi_material/in/edit_material_in', $data);
		$this->load->view('templates/footer');
	}

	function proses_edit_material_in($no_doc)
	{
		if (isset($_POST['simpan'])) {
			$no_doc = $this->input->post('no_doc');
			$id_stock =$this->input->post('id_stock');
			$material = $this->input->post('material');
			$quantity = $this->input->post('quantity');
			$uom = $this->input->post('uom');
			$date = $this->input->post('date');
			$shift = $this->input->post('shift');
			$nik = $this->input->post('nik');

			$data = array(
				'no_doc' =>$no_doc,
				'id_stock' =>$id_stock,
				'material' => $material,
				'quantity' => $quantity,
				'uom' => $uom,
				'date' =>$date,
				'shift' => $shift,
				'nik' => $nik
			);

			$this->db->where('no_doc', $no_doc);
			$this->db->update('tb_migo', $data);
			redirect('transaksi_material/material_in');
		}
	}

	function view_material_in($no_doc)
	{
		if (!$no_doc) {
			redirect('transaksi_material/material_in');
		}

		$data = array(
			'title' => "View Material IN",
			'viewMaterial' => $this->transaksi_material->get_transaksi_material_in($no_doc)
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('transaksi_material/in/view_material_in', $data);
		$this->load->view('templates/footer');
	}

	function delete_material_in($no_doc)
	{
		$this->db->delete('tb_migo', array('no_doc' => $no_doc));
		redirect('transaksi_material/material_in');
	}

	function get_json_mateial_in($search = null)
	{
		$data = $this->transaksi_material->get_json_material_in($search);
		if ($data) {
			echo json_encode($data);
		} else {
			echo "DATA TIDAK DITEMUKAN";
		}
	}
	// end controller material in

	/* ================================================================================================== */

	// start controller material out
	function material_out($search = null)
	{
		$data = array(
			'title' => "Consumption",
			'materialOut' => $this->transaksi_material->get_material_out($search)
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('transaksi_material/out/index', $data);
		$this->load->view('templates/footer',$data);
	}

	function add_material_out()
	{
		$data = array(
			'title' => "Add Material Out",
			'allMaterial' => $this->master_material->get_all_material(),
			'allStocks' => $this->stocks->get_all_stocks(),
			'allUsers' => $this->sysconfig->get_all_user()
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('transaksi_material/out/add_material_out', $data);
		$this->load->view('templates/footer');
	}

	function proses_add_material_out()
	{
		if (isset($_POST['simpan'])) {
			$mat_doc = $this->input->post('mat_doc');
			$id_stock =$this->input->post('id_stock');
			$material = $this->input->post('material');
			$quantity = $this->input->post('quantity');
			$uom = $this->input->post('uom');
			$date = $this->input->post('date');
			$shift = $this->input->post('shift');
			$nik = $this->input->post('nik');

			/*
			// untuk men-generate kode
			$id_use = $this->transaksi_material->get_kode_material_out();
			if ($id_use['kode_material_out']) {
				$id_use =$id_use['kode_material_out'];
				$substrKodeMaterial = substr($id_use, 1);
				$generated = (int)$substrKodeMaterial + 1;
				$$id_use = "MO-$generated";
				$kode = $id_use;
			} else {
				$kode = "MO-1";
			}
			// end untuk men-generate kode

			// untuk validasi kode yang sudah digunakan
			$getKodeMaterial = $this->transaksi_material->get_material_out($kode);

			if ($getKodeMaterial) {
				echo "<script>
						alert('kode material out sudah digunakan.');
						window.history.back();
					  </script>";
				return false;
			}*/
			// end untuk validasi kode yang sudah digunakan

			$data = array(
				'mat_doc' => $mat_doc,
				'id_stock' =>$id_stock,
				'material' => $material,
				'quantity' => $quantity,
				'uom' => $uom,
				'date' => $date,
				'shift'=>$shift,
				'nik' => $nik
			);

			$this->db->set('mat_doc', FALSE);
			$this->db->insert('tb_consumption', $data);
			redirect('transaksi_material/material_out');
		}
	}

	function edit_material_out($mat_doc)
	{
		if ($mat_doc == null) {
			redirect('transaksi_material/material_out');
		}

		$data = array(
			'title' => "Edit Material OUT",
			'allMaterial' => $this->master_material->get_all_material(),
			'allStocks' => $this->stocks->get_all_stocks(),
			'allUsers' => $this->sysconfig->get_all_user(),
			'editMaterial' => $this->transaksi_material->get_transaksi_material_out($mat_doc)
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('transaksi_material/out/edit_material_out', $data);
		$this->load->view('templates/footer');
	}

	function proses_edit_material_out($mat_doc)
	{
		if (isset($_POST['simpan'])) {
			$mat_doc = $this->input->post('mat_doc');
			$id_stock =$this->input->post('id_stock');
			$material = $this->input->post('material');
			$quantity = $this->input->post('quantity');
			$uom = $this->input->post('uom');
			$date = $this ->input->post('date');
			$shift = $this->input->post('shift');
			$nik = $this ->input->post('nik');

			$data = array(
				'mat_doc' => $mat_doc,
				'id_stock' =>$id_stock,
				'material' => $material,
				'quantity' => $quantity,
				'uom' => $uom,
				'date' => $date,
				'shift'=>$shift,
				'nik' => $nik
			);

			$this->db->where('mat_doc', $mat_doc);
			$this->db->update('tb_consumption', $data);
			redirect('transaksi_material/material_out');
		}
	}

	function view_material_out($mat_doc)
	{
		if (!$mat_doc) {
			redirect('transaksi_material/material_out');
		}

		$data = array(
			'title' => "View Material OUT",
			'viewMaterial' => $this->transaksi_material->get_transaksi_material_out($mat_doc)
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('transaksi_material/out/view_material_out', $data);
		$this->load->view('templates/footer');
	}

	function delete_material_out($mat_doc)
	{
		$this->db->delete('tb_consumption', array('mat_doc' => $mat_doc));
		redirect('transaksi_material/material_out');
	}

	function get_json_mateial_out($search = null)
	{
		$data = $this->transaksi_material->get_json_material_out($search);
		if ($data) {
			echo json_encode($data);
		} else {
			echo "DATA TIDAK DITEMUKAN";
		}
	}
	// end controller material out

}
