package com.ylt.toursdeliberations.model

import com.google.gson.annotations.SerializedName

class Deliberations(@SerializedName("delib_matiere_code") val delibMatiereCode: String,
                    @SerializedName("delib_date") val delibDate: String,
                    @SerializedName("delib_objet") val delibObjet: String,
                    @SerializedName("coll_nom") val collNom: String,
                    @SerializedName("delib_id") val delibId: String,
                    @SerializedName("cr_url") val crUrl: CR,
                    @SerializedName("vote_effectif") val voteEffectifs: Int,
                    @SerializedName("delib_url") val delibUrl: Delib,
                    @SerializedName("coll_siret") val collSiret: String,
                    @SerializedName("vote_contre") val coteContre: Int,
                    @SerializedName("delib_matiere_nom") val delibMatiereNom: String,
                    @SerializedName("themes") val themes: String,
                    @SerializedName("type_seance") val TypeSeance: String,
                    @SerializedName("vote_reel") val voteReel: Int,
                    @SerializedName("vote_abstention") val voteAbstention: Int,
                    @SerializedName("vote_pour") val votePour: Int,
                    @SerializedName("pref_id") val prefId: String,
                    @SerializedName("pref_date") val prefDate: String){
    constructor(delibObjet: String, collNom: String): this("", "", delibObjet, collNom, "" , CR("","", "", "", "", ""), 0, Delib("","", "", "", "", ""), "", 0, "", "", "", 0, 0, 0,"", "")

    override fun toString(): String {
        return "Deliberations(delibMatiereCode='$delibMatiereCode', delibDate='$delibDate', delibObjet='$delibObjet', collNom='$collNom', delibId='$delibId', voteEffectifs=$voteEffectifs, collSiret='$collSiret', coteContre=$coteContre, delibMatiereNom='$delibMatiereNom', themes='$themes', TypeSeance='$TypeSeance', voteReel=$voteReel, voteAbstention=$voteAbstention, votePour=$votePour, prefId='$prefId', prefDate='$prefDate')"
    }
}