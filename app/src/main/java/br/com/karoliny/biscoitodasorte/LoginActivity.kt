package br.com.karoliny.biscoitodasorte

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_loginctivity.*

class Loginctivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginctivity)

        //Recuperando o email da intende
        val emailRecuperado = intent.getStringExtra("email");

        //Abrindo o Shared  Preference
        val minhaPreferencia = getSharedPreferences("cadastro-$emailRecuperado", Context.MODE_PRIVATE);

        //Recuperandi os dados do Shared Prefenrence
        val senhaCadastrada = minhaPreferencia.getString("senha","Chave nao encontrada")


        //Clique do botão entrar
        btnEntrar.setOnClickListener {
            //Capturando texto digitado pelo usuário nos EditTexts
            val usuario = edtUsuario.text.toString().trim()
            val senha = edtSenhaLogin.text.toString().trim()

            //Verificar se existe algum texto digitado
            if (usuario.isEmpty()) {
                edtUsuario.error = "Campo obrigatório"
                edtUsuario.requestFocus()
            } else if (senha.isEmpty()) {
                edtSenhaLogin.error = "Campo obrigatório"
                edtSenhaLogin.requestFocus()
            } else if (usuario == emailRecuperado && senha == senhaCadastrada) {
                //Apresentando uma mensagem ao usuário
                Toast.makeText(this@LoginActivity, "Usuário logado com sucesso!", Toast.LENGTH_LONG)
                    .show()
                //Abrir a tela main
                startActivity(Intent(this@LoginActivity, MainActivity::class.java).apply {
                    putExtra("email", emailRecuperado
                    )
                })
                //Tirar a tela do empilhamento
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "Usuário ou senha incorretos", Toast.LENGTH_LONG)
                    .show()
            }
        }

        //Abrindo a tela de cadastro
        btnCadastrarLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, CadastroActivity::class.java))
        }
    }
}