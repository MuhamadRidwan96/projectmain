<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Auth extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();

        $this->load->model('Auth_model', 'auth');
    }

    public function index()
    {
        if ($this->session->akses) {
            redirect('stocks');
        }

        if (isset($_POST['login'])) {
            $username = $this->input->post('username');
            $password = $this->input->post('password');

            $akses = $this->auth->getAkses($username, $password);

            if ($akses) {
                $this->session->akses = $akses;
                $this->session->username = $akses['username'];
                $this->session->password = $akses['password'];

                $data = [
                    'akses' => $akses,
                    'username' => $akses['username'],
                    'password' => $akses['password']
                ];
                $this->session->set_userdata($data);
                echo "<script>
                        alert('anda berhasil login');
                        location='" . base_url('stocks/index') . "'
                      </script>";
            } else {
                echo "<script>
                        alert('username atau password salah');
                        location='" . base_url('auth') . "';
                      </script>";
            }
        }
        $data = [
            'title' => 'Login'
        ];
        $this->load->view('login', $data);
    }

    public function logout()
    {
        session_destroy();

        echo "<script>
                alert('Anda berhasil logout');
              </script>";
        echo "<script>
                location='" . base_url('welcome') . "'
              </script>";
    }
}
