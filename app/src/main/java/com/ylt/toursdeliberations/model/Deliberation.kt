package com.ylt.toursdeliberations.model

import com.google.gson.annotations.SerializedName

class Deliberation(@SerializedName("delib_matiere_code") val delibMatiereCode: String,
                   @SerializedName("delib_date") val delibDate: String,
                   @SerializedName("delib_objet") val delibObjet: String,
                   @SerializedName("coll_nom") val collNom: String,
                   @SerializedName("delib_id") val delibId: String,
                   @SerializedName("cr_url") val crUrl: CR,
                   @SerializedName("vote_effectif") val voteEffectifs: Int,
                   @SerializedName("delib_url") val delibUrl: Delib,
                   @SerializedName("coll_siret") val collSiret: String,
                   @SerializedName("vote_contre") val voteContre: Int,
                   @SerializedName("delib_matiere_nom") val delibMatiereNom: String,
                   @SerializedName("themes") val themes: String,
                   @SerializedName("type_seance") val typeSeance: String,
                   @SerializedName("vote_reel") val voteReel: Int,
                   @SerializedName("vote_abstention") val voteAbstention: Int,
                   @SerializedName("vote_pour") val votePour: Int,
                   @SerializedName("pref_id") val prefId: String,
                   @SerializedName("pref_date") val prefDate: String) {

    override fun toString(): String {
        return "Deliberations(delibMatiereCode='$delibMatiereCode', delibDate='$delibDate', delibObjet='$delibObjet', collNom='$collNom', delibId='$delibId', voteEffectifs=$voteEffectifs, collSiret='$collSiret', coteContre=$voteContre, delibMatiereNom='$delibMatiereNom', themes='$themes', TypeSeance='$typeSeance', voteReel=$voteReel, voteAbstention=$voteAbstention, votePour=$votePour, prefId='$prefId', prefDate='$prefDate')"
    }
}

/*
{
    "datasetid":"deliberations-tours-metropole-val-de-loire",
    "recordid":"43e3d7d579076a06fc577a1044dfeb5c6c9c1942",
    "fields": {
        "type_seance":"Conseil Métropolitain",
        "delib_matiere_code":"5.1.2",
        "delib_objet":"DETERMINATION DU NOMBRE DE VICE-PRESIDENTS ET DES AUTRES MEMBRES DU BUREAU",
        "coll_nom":"Tours Métropole Val de Loire",
        "delib_id":"C_21_07_11_002",
        "cr_url":{
            "format":"pdf",
            "filename":"1626905209_cr_30638.pdf",
            "width":300,
            "id":"e8783871774f15f0235f55c238d8e991",
            "height":300,
            "thumbnail":false
        },
    "vote_effectif":87,
    "delib_url":{
        "format":"pdf",
        "filename":"1626905209_30637.pdf",
        "width":300,
        "id":"884eeb7e670e958b4360e3bbf06889e1",
        "height":300,
        "thumbnail":false
    },
        "coll_siret":24370075400035,
        "delib_date":"2021-07-21",
        "delib_matiere_nom":"Institutions et vie politique Election executif Fixation du nombre des adjoints",
        "vote_pour":73,
        "vote_reel":87,
        "vote_abstention":7,
        "vote_contre":5,
        "pref_id":"SPREF0372",
        "pref_date":"2021-07-21"
    },
    "record_timestamp":"2021-07-21T23:25:00+00:00"
},

 */