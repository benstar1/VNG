SELECT
     t_operation_parcel."opv_date_op" AS t_operation_parcel_opv_date_op,
     t_operation_parcel."opv_prix" AS t_operation_parcel_opv_prix,
     t_operation_parcel."opv_pba_numero" AS t_operation_parcel_opv_pba_numero,
     t_operation_parcel."opv_mac_code" AS t_operation_parcel_opv_mac_code,
     t_operation_parcel."opv_desi_code" AS t_operation_parcel_opv_desi_code,
     t_operation_parcel."opv_desi_reference" AS t_operation_parcel_opv_desi_reference,
     t_operation_parcel."opv_desi_date" AS t_operation_parcel_opv_desi_date,
     t_operation_parcel."opv_nom_maire" AS t_operation_parcel_opv_nom_maire,
     t_operation_parcel."opv_numero_affirmation" AS t_operation_parcel_opv_numero_affirmation,
     t_operation_parcel."opv_nom_interprete" AS t_operation_parcel_opv_nom_interprete,
     t_operation_parcel."opv_date_saisie" AS t_operation_parcel_opv_date_saisie,
     t_operation_parcel."opv_date_validation" AS t_operation_parcel_opv_date_validation,
     t_intervenant."int_nom" AS t_intervenant_int_nom,
     t_intervenant."int_prenom" AS t_intervenant_int_prenom,
     t_intervenant."int_collectivite" AS t_intervenant_int_collectivite,
     t_intervenant."int_sexe" AS t_intervenant_int_sexe,
     t_intervenant."int_domicile" AS t_intervenant_int_domicile
FROM
     "public"."t_operation_parcel" t_operation_parcel INNER JOIN "public"."t_intervenir" t_intervenir ON t_operation_parcel."opv_numero" = t_intervenir."inv_opv_numero"
     AND t_operation_parcel."opv_numero" = t_intervenir."inv_opv_numero_preneur"
     INNER JOIN "public"."t_intervenant" t_intervenant ON t_intervenir."inv_int_numero" = t_intervenant."int_numero"
     AND t_intervenant."int_numero" = t_operation_parcel."opv_int_numero_bailleur"
     AND t_intervenant."int_numero" = t_operation_parcel."opv_int_numero_preneur"