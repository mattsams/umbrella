package io.mattsams.umbrella.binding

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
fun <V : View> Activity.bindView(@IdRes resId: Int) = BindViewDelegate(ViewResolver(this, resId) { activity, id ->
    activity.findViewById<V>(id)
})

@Suppress("UNCHECKED_CAST")
fun <V : View> View.bindView(@IdRes resId: Int) = BindViewDelegate(ViewResolver(this, resId) { view, id ->
    view.findViewById<V>(id)
})

@Suppress("UNCHECKED_CAST")
fun <V : View> RecyclerView.ViewHolder.bindView(@IdRes resId: Int) = BindViewDelegate(ViewResolver(this, resId) { holder, id ->
    holder.itemView.findViewById<V>(id)
})

class BindViewDelegate<in R, out V : View>(private val resolver: ViewResolver<R, V>) : ReadOnlyProperty<R, V> {
    private val view by lazy { resolver.resolve() }

    override fun getValue(thisRef: R, property: KProperty<*>) = view
}