<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Home extends CI_Controller
{

	function __construct()
	{
		parent::__construct();
		$this->load->model('Model_master_material', 'master_material');
		$this->load->model('Model_master_stocks','stocks');

		if (!$this->session->akses) {
			redirect('auth');
		}
	}

	function index()
	{
		$data = array(
			'title' => "Sistem Material",
			'allStocks' => $this->stocks->get_all_stocks()
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('stocks/index', $data);
		$this->load->view('templates/footer');
	}

	// data json
	function get_all_material($search = null)
	{
		$jsonAllMaterial = $this->master_material->get_all_material($search);

		$temp = array();
		$response = array();
		foreach ($jsonAllMaterial as $keyMatAll => $valueMatAll) {
			$temp[] = array(
				'mm' => $valueMatAll['mm'],
				'item' => $valueMatAll['item'],
				'color' => $valueMatAll['color']
			);
		}

		$response['materials'] = $temp;

		if ($response['materials']) {
			echo json_encode($response);
			return TRUE;
		} else {
			$response["message"] = "DATA NOT EXIST";
			return FALSE;
		}
	}

	function get_material_in($search = null)
	{
		$jsonMaterialIn = $this->master_material->get_material_in($search);

		$temp = array();
		$response = array();
		foreach ($jsonMaterialIn as $keyMatIn => $valueMatIn) {
			$temp[] = array(
				'id_in' => $valueMatIn['id_in'],
				'date' => $valueMatIn['date'],
				'shift' => $valueMatIn['shift'],
				'username' => $valueMatIn['username'],
				'code_location'=>$valueMatIn['code_location'],
				'mm' => $valueMatIn['mm'],
				'item' => $valueMatIn['item'],
				'quantity' => $valueMatIn['qunatity']
			);
		}

		$response['materials'] = $temp;

		if ($response['materials']) {
			echo json_encode($response);
			return TRUE;
		} else {
			$response["message"] = "DATA NOT EXIST";
			return FALSE;
		}
	}

	function get_material_out($search = null)
	{
		$jsonMaterialOut = $this->master_material->get_material_out($search);

		$temp = array();
		$response = array();
		foreach ($jsonMaterialOut as $keyMatOut => $valueMatOut) {
			$temp[] = array(
				'id_out' => $valueMatOut['id_out'],
				'date' => $valueMatOut['date'],
				'shift' => $valueMatOut['shift'],
				'username' => $valueMatOut['username'],
				'code_location'=>$valueMatOut['code_location'],
				'mm' => $valueMatOut['mm'],
				'item' => $valueMatOut['item'],
				'quantity' => $valueMatOut['qunatity']
				
			);
		}

		$response['materials'] = $temp;

		if ($response['materials']) {
			echo json_encode($response);
			return TRUE;
		} else {
			$response["message"] = "DATA NOT EXIST";
			return FALSE;
		}
	}
	// end data json

}
