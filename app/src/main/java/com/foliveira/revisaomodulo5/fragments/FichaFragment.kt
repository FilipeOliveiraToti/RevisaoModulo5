package com.foliveira.revisaomodulo5.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.foliveira.revisaomodulo5.R

class FichaFragment : Fragment() {
    var textViewNome: TextView? = null
    var textViewRaca: TextView? = null
    var textViewClasse: TextView? = null
    var textViewForca: TextView? = null
    var textViewAutoCura: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ficha, container, false)

        textViewNome = view.findViewById(R.id.textViewNome)
        textViewRaca = view.findViewById(R.id.textViewRaca)
        textViewClasse = view.findViewById(R.id.textViewClasse)
        textViewForca = view.findViewById(R.id.textViewForca)
        textViewAutoCura = view.findViewById(R.id.textViewAutoCura)

        // Receber dados do Bundle
        val nome = arguments?.getString("nome") ?: ""
        val raca = arguments?.getString("raca") ?: ""
        val classe = arguments?.getString("classe") ?: ""
        val forca = arguments?.getInt("forca") ?: 0
        val autocura = if (arguments?.getBoolean("autocura") == true) "Sim" else "Não"

        // Exibir dados na tela
        textViewNome?.text = "Nome: $nome"
        textViewRaca?.text = "Raça: $raca"
        textViewClasse?.text = "Classe: $classe"
        textViewForca?.text = "Força: $forca"
        textViewAutoCura?.text = "Possui autocura? $autocura"

        return view
    }

    companion object {

        fun newInstance(
            nome: String,
            raca: String,
            classe: String,
            forca: Int,
            autocura: Boolean
        ) : FichaFragment {
            val fragment = FichaFragment()
            val args = Bundle()

            args.putString("nome", nome)
            args.putString("raca", raca)
            args.putString("classe", classe)
            args.putInt("forca", forca)
            args.putBoolean("autocura", autocura)

            fragment.arguments = args
            return fragment
        }
    }
}