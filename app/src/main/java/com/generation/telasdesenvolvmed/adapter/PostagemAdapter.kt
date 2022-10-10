package com.generation.telasdesenvolvmed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.generation.telasdesenvolvmed.MainViewModel
import com.generation.telasdesenvolvmed.databinding.CardLayoutBinding
import com.generation.telasdesenvolvmed.model.Postagem

class PostagemAdapter(
	val mainViewModel: MainViewModel
) : RecyclerView.Adapter<PostagemAdapter.PostagemViewHolder>() {

	private var listPostagem = emptyList<Postagem>()

	class PostagemViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
		return PostagemViewHolder(
			CardLayoutBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
		val postagem = listPostagem[position]

		holder.binding.temaPost.text = postagem.tema.tema
		holder.binding.nomeMedico.text = postagem.medico.cadastro!!.nome
		holder.binding.tituloPost.text = postagem.titulo
		holder.binding.conteudoPost.text = postagem.descricao
		holder.binding.linkAnexo.text = postagem.anexo

		if(mainViewModel.pacienteLogado.value?.body()?.toString() != null){
			holder.binding.botaoEditarPost.visibility = View.INVISIBLE
			holder.binding.botaoDeletarPost.visibility = View.INVISIBLE
		}

		if(mainViewModel.medicoLogado.value?.body()?.cadastro?.nome.toString() != postagem.medico.cadastro!!.nome){
			holder.binding.botaoEditarPost.visibility = View.INVISIBLE
			holder.binding.botaoDeletarPost.visibility = View.INVISIBLE
		}



	}

	override fun getItemCount(): Int {
		return listPostagem.size
	}

	fun setList(list: List<Postagem>) {
		listPostagem = list
		notifyDataSetChanged()
	}

}