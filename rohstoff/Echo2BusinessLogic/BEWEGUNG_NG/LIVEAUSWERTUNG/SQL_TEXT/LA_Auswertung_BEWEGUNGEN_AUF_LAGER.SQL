SELECT
    MAX(BASE.ID_BEWEGUNGSATOM)       AS ID_BASIS,
    SUM(BASE.MENGE_EINGANG_EK)       AS SUMME_EINGANG_EK,
    SUM(BASE.ABZUG_MENGE_EINGANG_EK) AS SUMME_ABZUG_EINGANG_EK,
    SUM(BASE.MENGE_AUSGANG_VK)       AS SUMME_AUSGANG_VK,
    SUM(BASE.ABZUG_MENGE_AUSGANG_VK) AS SUMME_ABZUG_AUSGANG_VK,
    SUM(BASE.MENGE_EINGANG_MK)       AS SUMME_EINGANG_MK,
    SUM(BASE.MENGE_EINGANG_KH)       AS SUMME_EINGANG_KH,
    SUM(BASE.MENGE_EINGANG_UH)       AS SUMME_EINGANG_UH,
    SUM(BASE.MENGE_EINGANG_LL)       AS SUMME_EINGANG_LL,
    SUM(BASE.MENGE_EINGANG_SW)       AS SUMME_EINGANG_SW,
    SUM(BASE.MENGE_EINGANG_MI)       AS SUMME_EINGANG_MI,
    SUM(BASE.MENGE_AUSGANG_MK)       AS SUMME_AUSGANG_MK,
    SUM(BASE.MENGE_AUSGANG_KH)       AS SUMME_AUSGANG_KH,
    SUM(BASE.MENGE_AUSGANG_UH)       AS SUMME_AUSGANG_UH,
    SUM(BASE.MENGE_AUSGANG_LL)       AS SUMME_AUSGANG_LL,
    SUM(BASE.MENGE_AUSGANG_SW)       AS SUMME_AUSGANG_SW,
    SUM(BASE.MENGE_AUSGANG_MI)       AS SUMME_AUSGANG_MI ,
    BASE.ID_ADRESSE_LAGER,
    BASE.ANR1,
    BASE.ID_ARTIKEL,
    BASE.JAHR_MONAT,
    BASE.JAHR_MONAT_LESBAR
FROM
    (
        SELECT
            ATOM.ID_BEWEGUNGSATOM,
            NVL(TO_CHAR(ATOM.LEISTUNGSDATUM,'YYYY-MM'),'----.--')  AS JAHR_MONAT,
            NVL(TO_CHAR(ATOM.LEISTUNGSDATUM,'MON YYYY'),'----.--') AS JAHR_MONAT_LESBAR,
            ART.ANR1                                               AS ANR1,
            ART.ID_ARTIKEL                                         AS ID_ARTIKEL,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_START=0)
                THEN ATOM.ID_ADRESSE_LADESTATION
                ELSE ATOM.ID_ADRESSE_ABLADESTATION
            END AS ID_ADRESSE_LAGER,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_START=0
                    AND ATOM.ID_TYP_LAGER_ZIEL=1)
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_AUSGANG_VK,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_START=0
                    AND ATOM.ID_TYP_LAGER_ZIEL=1)
                THEN NVL(ATOM.ABZUG_MENGE,0)
                ELSE 0
            END AS ABZUG_MENGE_AUSGANG_VK,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_ZIEL=0
                    AND ATOM.ID_TYP_LAGER_START=1)
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_EINGANG_EK,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_ZIEL=0
                    AND ATOM.ID_TYP_LAGER_START=1)
                THEN NVL(ATOM.ABZUG_MENGE,0)
                ELSE 0
            END AS ABZUG_MENGE_EINGANG_EK,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_START=0
                    AND ATOM.ID_TYP_LAGER_ZIEL=3
                    AND ATOM.ATTRIB_LAGER_ZIEL='MK')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_AUSGANG_MK,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_START=0
                    AND ATOM.ID_TYP_LAGER_ZIEL=3
                    AND ATOM.ATTRIB_LAGER_ZIEL='KH')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_AUSGANG_KH,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_START=0
                    AND ATOM.ID_TYP_LAGER_ZIEL=3
                    AND ATOM.ATTRIB_LAGER_ZIEL='UH')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_AUSGANG_UH,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_START=0
                    AND ATOM.ID_TYP_LAGER_ZIEL=3
                    AND ATOM.ATTRIB_LAGER_ZIEL='LL')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_AUSGANG_LL,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_START=0
                    AND ATOM.ID_TYP_LAGER_ZIEL=3
                    AND ATOM.ATTRIB_LAGER_ZIEL='SW')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_AUSGANG_SW,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_START=0
                    AND ATOM.ID_TYP_LAGER_ZIEL=3
                    AND ATOM.ATTRIB_LAGER_ZIEL='MI')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_AUSGANG_MI,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_ZIEL=0
                    AND ATOM.ID_TYP_LAGER_START=3
                    AND ATOM.ATTRIB_LAGER_START='MK')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_EINGANG_MK,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_ZIEL=0
                    AND ATOM.ID_TYP_LAGER_START=3
                    AND ATOM.ATTRIB_LAGER_START='KH')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_EINGANG_KH,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_ZIEL=0
                    AND ATOM.ID_TYP_LAGER_START=3
                    AND ATOM.ATTRIB_LAGER_START='UH')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_EINGANG_UH,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_ZIEL=0
                    AND ATOM.ID_TYP_LAGER_START=3
                    AND ATOM.ATTRIB_LAGER_START='LL')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_EINGANG_LL,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_ZIEL=0
                    AND ATOM.ID_TYP_LAGER_START=3
                    AND ATOM.ATTRIB_LAGER_START='SW')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_EINGANG_SW,
            CASE
                WHEN (ATOM.ID_TYP_LAGER_ZIEL=0
                    AND ATOM.ID_TYP_LAGER_START=3
                    AND ATOM.ATTRIB_LAGER_START='MI')
                THEN NVL(ATOM.MENGE,0)
                ELSE 0
            END AS MENGE_EINGANG_MI
        FROM
            JT_BEWEGUNGSATOM ATOM
        LEFT OUTER JOIN
            JT_BEWEGUNGSVEKTOR VEKT
        ON
            (
                ATOM.ID_BEWEGUNGSVEKTOR = VEKT.ID_BEWEGUNGSVEKTOR)
        LEFT OUTER JOIN
            JT_BEWEGUNG BEW
        ON
            (
                VEKT.ID_BEWEGUNG = BEW.ID_BEWEGUNG)
        LEFT OUTER JOIN
            JT_ARTIKEL_BEZ AB
        ON
            (
                ATOM.ID_ARTIKEL_BEZ = AB.ID_ARTIKEL_BEZ)
        LEFT OUTER JOIN
            JT_ARTIKEL ART
        ON
            (
                AB.ID_ARTIKEL = ART.ID_ARTIKEL)
        LEFT OUTER JOIN
            JT_ADRESSE ADR
        ON
            (
                ATOM.ID_ADRESSE_LADESTATION = ADR.ID_ADRESSE)
        WHERE
            ATOM.ID_TYP_LAGER_START = 0
        OR  ATOM.ID_TYP_LAGER_ZIEL = 0 ) BASE
GROUP BY
    BASE.ID_ADRESSE_LAGER,
    BASE.ANR1,
    BASE.ID_ARTIKEL,
    BASE.JAHR_MONAT,
    BASE.JAHR_MONAT_LESBAR 