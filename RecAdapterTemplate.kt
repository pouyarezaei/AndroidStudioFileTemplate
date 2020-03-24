#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}
/**
 * 
 *     Made By Pouya Rezaei
 *     Github : pouyarezaei
 *     RecyclerView Template Adapter
 *
 */
#end

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ${NAME}(
    private val items: List<${ModelName}>,
    private val recyclerClick: RecyclerViewItemClick
) : RecyclerView.Adapter<${NAME}.${ViewHolderName}>() {


    inner class ${ViewHolderName}(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: ${ModelName}, recyclerClick: RecyclerViewItemClick) {
            recyclerClick.click(model)
            // TODO binding views
        }
    }

    interface RecyclerViewItemClick {
        fun click(model: ${ModelName});
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ${ViewHolderName} {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.${LayoutName}, parent, false);
        return ${ViewHolderName}(view);
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ${ViewHolderName}, position: Int) {
        holder.bind(items[position], recyclerClick)
    }

}