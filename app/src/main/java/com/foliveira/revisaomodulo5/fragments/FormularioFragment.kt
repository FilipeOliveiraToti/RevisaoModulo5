package com.foliveira.revisaomodulo5.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import com.foliveira.revisaomodulo5.R

class FormularioFragment : Fragment() {
    lateinit var editTextNome: EditText
    lateinit var radioGroupRaca: RadioGroup
    lateinit var radioGroupClasse: RadioGroup
    lateinit var seekBarForca: SeekBar
    lateinit var checkboxAutocura: CheckBox
    lateinit var buttonConfirmar: Button
    lateinit var textViewForca: TextView

    var selectedRaca: String = ""
    var selectedClasse: String = ""
    var forca: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_formulario, container, false)

        editTextNome = view.findViewById(R.id.editTextNome)
        radioGroupRaca = view.findViewById(R.id.radioGroupRaca)
        radioGroupClasse = view.findViewById(R.id.radioGroupClasse)
        seekBarForca = view.findViewById(R.id.seekBarForca)
        checkboxAutocura = view.findViewById(R.id.checkBoxAutoCura)
        buttonConfirmar = view.findViewById(R.id.buttonConfirmar)
        textViewForca = view.findViewById(R.id.textViewForca)

        // Configurar listeners para os RadioGroups

        radioGroupRaca.setOnCheckedChangeListener { _, checkedId ->
            // Obter a String do RadioButton de raça selecionado
            selectedRaca = radioGroupRaca.findViewById<RadioButton>(checkedId)?.text.toString()
        }

        radioGroupClasse.setOnCheckedChangeListener { _, checkedId ->
            // Obter a String do RadioButton de classe selecionado
            selectedClasse = radioGroupClasse.findViewById<RadioButton>(checkedId)?.text.toString()
        }

        seekBarForca.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Atualizar a variável forca com o valor do SeekBar
                forca = progress
                textViewForca.text = "Força: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        buttonConfirmar.setOnClickListener {
            // Criar AlertDialog para confirmar o envio
            AlertDialog.Builder(requireContext())
                .setTitle("Confirmar Criação")
                .setMessage("Deseja confirmar a criação do personagem?")
                .setPositiveButton("Sim") { dialog, _ ->

                    // Enviar Bundle para o segundo Fragment
                    val fragment2 = FichaFragment.newInstance(
                        nome = editTextNome.text.toString(),
                        raca = selectedRaca,
                        classe = selectedClasse,
                        forca = seekBarForca.progress,
                        autocura = checkboxAutocura.isChecked
                    )

                    // Substituir o Fragment atual pelo segundo Fragment
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment2)
                        .addToBackStack(null)
                        .commit()

                    dialog.dismiss()
                }
                .setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        return view
    }
}