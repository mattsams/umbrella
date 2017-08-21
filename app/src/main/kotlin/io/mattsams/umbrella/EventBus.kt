package io.mattsams.umbrella

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object EventBus {
    private val subject = BehaviorSubject.create<Any>().toSerialized()

    fun emit(event: Any) = subject.onNext(event)

    fun <T : Any> listen(eventClass: Class<T>): Observable<T> =
            subject.filter { it.javaClass == eventClass }.cast(eventClass)

    inline fun <reified T : Any> listen(): Observable<T> = listen(T::class.java)
}