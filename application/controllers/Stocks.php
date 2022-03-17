<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Stocks extends CI_Controller
{

	function __construct()
	{
		parent::__construct();
		$this->load->model('Model_master_location','master_location');
        $this->load->model('Model_master_material', 'master_material');
        $this->load->model('Model_master_stocks','stocks');

		if (!$this->session->akses) {
			redirect('auth');
		}
	}

	function index()
	{
		$data = array(
			'title' => "Stocks",
			'allStocks' => $this->stocks->get_all_stocks()
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('stocks/index', $data);
		$this->load->view('templates/footer');
	}

	function add_stocks()
	{
		$data = array(
            'title' => "Add Stocks",
            'allStocks' =>$this->stocks->get_all_stocks(),
			'all' => $this->master_material->get_all_material(),
			'allLocation' =>$this->master_location->get_all_location()
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('stocks/add_stocks', $data);
		$this->load->view('templates/footer');
	}

	function proses_add_stocks()
	{
		if (isset($_POST['simpan'])) {
            $id_stock =$this->input->post('id_stock'); 
            $bin = $this->input->post('bin');
			$mm = $this->input->post('mm');
			$item = $this->input->post('item');
            $available_stock = $this->input->post('available_stock');
            $uom = $this->input->post('uom');
            $gr_date = $this->input->post('gr_date');
			/*
			$quantity = $this->input->post('quantity');
			$uom = $this->input->post('uom');
			$lokasi = $this->input->post('lokasi');*/

            // untuk men-generate kode
            /*
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

			// untuk validasi kode yang sudah digunakan
			$getMcode_location = $this->master_location->get_all_location($code_location);

			if ($getMcode_location) {
				echo "<script>
								alert('location already exicst.');
								window.history.back();
							</script>";
				return false;
			}
			// end untuk validasi kode yang sudah digunakan
				*/
			$data = array(
                'id_stock' => $id_stock,
                'bin' => $bin,
				'mm' => $mm,
                'item' => $item,
                'available_stock' => $available_stock,
                'uom' => $uom,
                'gr_date' => $gr_date
            
				/*
				'quantity' => $quantity,
				'uom' => $uom,
				'lokasi' => $lokasi*/
			);

			// print_r($data);
			// exit;

			$this->db->set('id_stock', FALSE);
            $this->db->insert('tb_stock', $data);
			redirect('stocks/index');
		}
	}

	function view_stocks($id_stock)
	{
		if (!$id_stock) {
			redirect('stoccks/index');
		}

		$data = array(
			'title' => "View Stocks",
			'viewStocks' => $this->stocks->get_stocks($id_stock));
		$this->load->view('templates/header', $data);
        $this->load->view('templates/sidebar', $data);
		$this->load->view('stocks/view_stocks', $data);
		$this->load->view('templates/footer');
	}

	function edit_stocks($id_stock)
	{
		if (!$id_stock) {
			redirect('stocks/index');
		}

		$data = array(
            'title' => "Edit Stocks",
			'editStocks' => $this->stocks->get_stocks($id_stock),
			'allMaterial' => $this->master_material->get_all_material(),
			'allLocation' =>$this->master_location->get_all_location());
		$this->load->view('templates/header', $data);
        $this->load->view('templates/sidebar', $data);
		$this->load->view('stocks/edit_stocks',$data);
		$this->load->view('templates/footer');
	}

	function proses_edit_stocks($id_stock)
	{
		if (isset($_POST['simpan'])) {
            $id_stock =$this->input->post('id_stock'); 
            $bin = $this->input->post('bin');
			$mm = $this->input->post('mm');
			$item = $this->input->post('item');
            $available_stock = $this->input->post('available_stock');
            $uom = $this->input->post('uom');
            $gr_date = $this->input->post('gr_date');
            

			$data = array(
                'id_stock' => $id_stock,
                'bin' => $bin,
				'mm' => $mm,
                'item' => $item,
                'available_stock' => $available_stock,
                'uom' => $uom,
                'gr_date' => $gr_date
			);

			// print_r($data);
			// exit;

			$this->db->where('id_stock', $id_stock);
			$this->db->update('tb_stock', $data);
			redirect('stocks/index');
		}
	}

	function delete_stocks($id_stock)
	{
		$this->db->delete('tb_stock', array('id_stock' => $id_stock));
		redirect('stocks/index');
	}

	function get_stock($search = null)
	{
		$data = $this->stocks->get_stock($search);
		if ($data) {
			echo json_encode($data);
		} else {
			echo "DATA TIDAK DITEMUKAN";
		}
	}
}
