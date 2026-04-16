# TODO - Sprint 1 : Page Insertion Nouveau Titre FITIA

## FRONTEND

### [ ] Section État Civil
- [ ] Nom (text)
- [ ] Prénom (text)
- [ ] Nom de jeune fille (text)
- [ ] Date de naissance (calendar)
- [ ] Situation familiale (dropdown - api)
- [ ] Nationalité (dropdown - api)
- [ ] Adresse (text)
- [ ] Profession (text)
- [ ] Email (text)
- [ ] Téléphone (text)

### [ ] Section Passeport
- [ ] Numéro du passeport (text)
- [ ] Date de délivrance (calendar)
- [ ] Date d'expiration (calendar)
- [ ] Validation UI : Date expiration > date actuelle

### [ ] Section Visa Transformable
- [ ] Référence (text)
- [ ] Date d'entrée (calendar)
- [ ] Lieu d'entrée (dropdown - api)
- [ ] Date d'expiration (calendar)
- [ ] Validation UI : Visa non expiré

### [ ] Section Type de Visa Demandé
- [ ] Type de visa demandé (dropdown - api)

### [ ] Section Pièces Justificatives
- [ ] Affichage dynamique selon type visa
- [ ] Pièces communes
- [ ] Pièces spécifiques au type de visa
- [ ] Checkbox "fourni" par pièce

### [ ] Validation Formulaire
- [ ] Champs obligatoires remplis
- [ ] Cohérence des dates
- [ ] Type visa sélectionné

### [ ] Bouton VALIDER

---

## BACKEND

### [ ] API GET
- [ ] GET /situations-familiales
- [ ] GET /nationalites
- [ ] GET /lieux
- [ ] GET /categories-demandes
- [ ] GET /types-visa
- [ ] GET /pieces?type=commune
- [ ] GET /pieces?typePiece={idTypePiece}
- [ ] GET /pieces?typeVisa={idTypeVisa}

### [ ] API POST
- [ ] POST /demandes

### [ ] Fonctions Service
- [ ] getSituationFamiliale()
- [ ] getNationalite()
- [ ] getLieu()
- [ ] getCategorieDemande()
- [ ] getTypeVisa()
- [ ] getTypePieceById(int id) : List<PieceType>
- [ ] getAllPieceCommune() : List<Piece>
- [ ] getPieceByType(int idPieceType) : List<Piece>

### [ ] Validation Préalable
- [ ] Champs obligatoires valides
- [ ] Passeport non expiré
- [ ] Visa non expiré
- [ ] Cohérence des données (personne/passeport/visa)

### [ ] Logique Métier (Transaction)
- [ ] Personne : vérifier existence, insertion si nouvelle
- [ ] EtatCivil : vérifier données, insertion
- [ ] Passeport : vérifier données, cohérence, expiration, insertion si nouveau
- [ ] Visa : vérifier données, expiration, insertion
- [ ] DemandeTitre : lier visa + passeport + état civil, status=CREE, catégorie=NOUVEAU_TITRE, insertion
- [ ] DemandePiece : charger pièces attendues, fourni=false, valide=false, dateDepot=null
- [ ] Commit / Rollback si erreur

---
# NOAH - Sprint 1

## FRONTEND

### [ ] Page Liste des Dossiers
- [ ] Tableau : ID, Nom, Prénom, Type Visa, Status, Date création, Complétude
- [ ] Filtres : status, type visa, date
- [ ] Recherche : nom/prénom/numéro passeport
- [ ] Pagination
- [ ] Bouton "Voir détails"
- [ ] Badge complet/incomplet

### [ ] Page Vérification Dossier
- [ ] Infos personne (lecture seule)
- [ ] Infos passeport (lecture seule)
- [ ] Infos visa (lecture seule)
- [ ] Liste pièces avec colonnes : libelle, obligatoire, fourni, validé
- [ ] Distinction visuelle pièces obligatoires vs optionnelles
- [ ] Checkbox "validé" par pièce
- [ ] Indicateur global complétude (basé sur pièces obligatoires)
- [ ] Bouton "Marquer comme complet"

### [ ] Page Modification Informations
- [ ] Édition personne : nom, prénom, nom_jeune_fille, date_naissance, situation_familiale, nationalite, adresse, profession, email, telephone
- [ ] Édition passeport : numero, date_delivrance, date_expiration
- [ ] Édition visa : reference, date_entree, lieu_entree, date_expiration
- [ ] Mêmes validations que l'insertion
- [ ] Bouton "Enregistrer"

---

## BACKEND

### [ ] API GET
- [ ] GET /demandes?status=&typeVisa=&page=&size=
- [ ] GET /demandes/{id}
- [ ] GET /demandes/{id}/pieces
- [ ] GET /demandes/{id}/completude

### [ ] API PUT
- [ ] PUT /personnes/{id}
- [ ] PUT /passeports/{id}
- [ ] PUT /visas/{id}
- [ ] PUT /demande-pieces/{id} (màj fourni/valide)
- [ ] PUT /demandes/{id}/status

### [ ] Fonctions Service
- [ ] getDemandesList(filtres, pageable) : Page<DemandeTitreDTO>
- [ ] getDemandeById(int id) : DemandeTitreDTO
- [ ] getPiecesByDemande(int idDemande) : List<DemandePieceDTO>
- [ ] checkCompletude(int idDemande) : CompletudDTO
- [ ] updatePersonne(int id, PersonneDTO)
- [ ] updatePasseport(int id, PasseportDTO)
- [ ] updateVisa(int id, VisaDTO)
- [ ] updateDemandePiece(int id, boolean fourni, boolean valide)
- [ ] updateDemandeStatus(int id, String status)

### [ ] Logique Vérification Complétude
- [ ] Récupérer pièces obligatoires via piece_type_visa.est_obligatoire = true
- [ ] Vérifier toutes pièces obligatoires fournies (demande_piece.fourni = true)
- [ ] Vérifier toutes pièces obligatoires validées (demande_piece.valide = true)
- [ ] Vérifier passeport.date_expiration > NOW()
- [ ] Vérifier visa.date_expiration > NOW()
- [ ] Retourner : isComplet, piecesManquantes[], piecesNonValidees[]

### [ ] DTOs
- [ ] DemandeTitreDTO (avec infos personne, passeport, visa, type_visa, status)
- [ ] DemandePieceDTO (avec libelle, est_obligatoire, fourni, valide, date_depot)
- [ ] CompletudDTO (isComplet, piecesManquantes, piecesNonValidees, passeportExpire, visaExpire)