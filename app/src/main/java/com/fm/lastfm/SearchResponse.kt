package com.fm.lastfm

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(

	@SerializedName("results")
	val results: Results? = null
) : Parcelable

@Parcelize
data class OpensearchQuery(

	@SerializedName("startPage")
	val startPage: String? = null,

	@SerializedName("#text")
	val text: String? = null,

	@SerializedName("role")
	val role: String? = null,

	@SerializedName("searchTerms")
	val searchTerms: String? = null
) : Parcelable

@Parcelize
data class AlbumItem(

	@SerializedName("image")
	val image: List<ImageItem?>? = null,

	@SerializedName("mbid")
	val mbid: String? = null,

	@SerializedName("artist")
	val artist: String? = null,

	@SerializedName("streamable")
	val streamable: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("url")
	val url: String? = null
) : Parcelable

@Parcelize
data class Attr(

	@SerializedName("for")
	val jsonMemberFor: String? = null
) : Parcelable

@Parcelize
data class ImageItem(

	@SerializedName("#text")
	val text: String? = null,

	@SerializedName("size")
	val size: String? = null
) : Parcelable

@Parcelize
data class Results(

	@SerializedName("opensearch:Query")
	val opensearchQuery: OpensearchQuery? = null,

	@SerializedName("@attr")
	val attr: Attr? = null,

	@SerializedName("opensearch:itemsPerPage")
	val opensearchItemsPerPage: String? = null,

	@SerializedName("albummatches")
	val albummatches: Albummatches? = null,

	@SerializedName("opensearch:startIndex")
	val opensearchStartIndex: String? = null,

	@SerializedName("opensearch:totalResults")
	val opensearchTotalResults: String? = null
) : Parcelable

@Parcelize
data class Albummatches(

	@SerializedName("album")
	val album: ArrayList<AlbumItem>? = null
) : Parcelable
