<?php
defined('BASEPATH') or exit('No direct script access allowed');

class SysConfig extends CI_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('Users_model', 'user','sysconfig');

        if (!$this->session->akses) {
            redirect('auth');
        }
    }

    // function user
    function index()
    {
        $data = array(
            'title' => "User",
            'allUser' => $this->user->get_all_user()
        );
        $this->load->view('templates/header', $data);
        $this->load->view('templates/sidebar', $data);
        $this->load->view('sysconfig/index', $data);
        $this->load->view('templates/footer');
    }
	// end function user
	
	function add_user()
	{
		$data = array(
			'title' => "Add User"
		);
		$this->load->view('templates/header', $data);
		$this->load->view('templates/sidebar', $data);
		$this->load->view('sysconfig/add_user', $data);
		$this->load->view('templates/footer');
	}

	function proses_add_user()
	{
		if (isset($_POST['simpan'])) {
			$nik = $this->input->post('nik');
			$username = $this->input->post('username');
			$password = $this->input->post('password');
			$full_name = $this->input->post('full_name');
			$phone = $this->input->post('phone');
			$email = $this->input->post('email');
	
			/*
			$quantity = $this->input->post('quantity');
			$uom = $this->input->post('uom');
			$lokasi = $this->input->post('lokasi');*/

			// untuk men-generate kode
			
			// end untuk men-generate kode

			// untuk validasi kode yang sudah digunakan
			$getMusers= $this->user->get_all_user($nik);

			if ($getMusers) {
				echo "<script>
								alert('nik sudah digunakan.');
								window.history.back();
							</script>";
				return false;
			}
			// end untuk validasi kode yang sudah digunakan

			$data = array(
				'nik' =>$nik,
				'username' => $username,
				'password' => $password,
				'full_name' => $full_name,
				'phone' => $phone,
				'email' => $email,
				/*
				'quantity' => $quantity,
				'uom' => $uom,
				'lokasi' => $lokasi*/
			);

			// print_r($data);
			// exit;

			$this->db->set('nik', FALSE);
			$this->db->insert('tb_users', $data);
			redirect('sysconfig/index');
		}
	}


    function edit_user($nik)
	{
		if (!$nik) {
			redirect('sysconfig/index');
		}

		$data = array(
			'title' => "Edit User",
			'editUser' => $this->user->getUser($nik)
		);
		$this->load->view('templates/header', $data);
        $this->load->view('templates/sidebar', $data);
        $this->load->view('sysconfig/edit_user', $data);
        $this->load->view('templates/footer');
	}

	function proses_edit_user($username)
	{
		if (isset($_POST['simpan'])) {
			$nik = $this->input->post('nik');
			$username = $this->input->post('username');
			$password = $this->input->post('password');
            $full_name = $this->input->post('full_name');
            $phone =$this->input->post('phone');
            $email =$this->input->post('email');
	
			$data = array(
				'nik' => $nik,
				'username' => $username,
				'password' => $password,
                'full_name' => $full_name,
                'phone' => $phone,
                'email' => $email
            
			
			);

			$this->db->where('nik', $nik);
			$this->db->update('tb_users', $data);
			redirect('sysconfig/index');
		}
	}

	function delete_user($nik)
	{
		$this->db->delete('tb_users', array('nik' => $nik));
		redirect('sysconfig/index');
	}

	function get_username($search = null)
	{
		$data = $this->user->get_username($search);
		if ($data) {
			echo json_encode($data);
		} else {
			echo "DATA TIDAK DITEMUKAN";
		}
	}

}
