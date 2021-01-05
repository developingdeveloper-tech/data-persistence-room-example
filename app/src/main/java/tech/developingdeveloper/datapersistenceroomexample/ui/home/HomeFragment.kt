package tech.developingdeveloper.datapersistenceroomexample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tech.developingdeveloper.datapersistenceroomexample.data.model.Book
import tech.developingdeveloper.datapersistenceroomexample.databinding.DialogAddBookBinding
import tech.developingdeveloper.datapersistenceroomexample.databinding.FragmentHomeBinding


/**
 * @author Abhishek Saxena
 * @since 03-01-2021 12:47
 */

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var materialDialog: AlertDialog

    private lateinit var materialDialogBinding: DialogAddBookBinding

    private lateinit var booksAdapter: BooksAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.addBookFab.setOnClickListener {
            createBookDialog(
                    "Add Book",
                    "Add Book"
            ) {
                addBookEvent()
            }
        }

        binding.booksRecyclerview.apply {
            booksAdapter = BooksAdapter { book ->
                createBookDialog(
                        "Update Book",
                        "Update Book",
                        book,
                        { deleteBookEvent() },
                        { updateBookEvent() }
                )
            }

            adapter = booksAdapter

            layoutManager =
                    LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    )

            val dividerItemDecoration = DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
            )
            addItemDecoration(dividerItemDecoration)
        }

        viewModel.books.observe(viewLifecycleOwner) {
            booksAdapter.submitList(it)
        }
    }

    private fun createBookDialog(
            title: String,
            positiveButtonText: String,
            book: Book? = null,
            neutralButtonAction: () -> Unit = {},
            positiveButtonAction: () -> Unit
    ) {

        materialDialogBinding =
                DialogAddBookBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        materialDialogBinding.lifecycleOwner = viewLifecycleOwner
        materialDialogBinding.viewModel = viewModel

        materialDialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setView(materialDialogBinding.root)
                .setPositiveButton(positiveButtonText) { _, _ -> positiveButtonAction() }
                .setNegativeButton("Cancel") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .apply {
                    book?.let {
                        viewModel.id = it.id
                        viewModel.title.value = it.title
                        viewModel.author.value = it.author
                        viewModel.pages.value = it.pages
                        this.setNeutralButton("Delete") { _, _ -> neutralButtonAction() }
                    }
                }
                .create()
                .also {
                    it.show()
                    it.setOnCancelListener {
                        viewModel.resetValues()
                    }
                    it.setOnDismissListener {
                        viewModel.resetValues()
                    }
                }
    }

    private fun addBookEvent() =
            viewModel.onInsertBook()

    private fun updateBookEvent() =
            viewModel.onUpdateBook()

    private fun deleteBookEvent() =
            viewModel.onDeleteBook()

}