package org.sonrohan.composemultiplatformcircuitplayground.parcel

// For Android @Parcelize
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class CommonParcelize

// For Android Parcelable
expect interface CommonParcelable