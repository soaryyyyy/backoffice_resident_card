# TODO - Sprint 1 : Page Insertion Nouveau Titre

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
- [ ] DemandeTitre : lier visa + passeport + état civil, status=EN_COURS, catégorie=NOUVEAU_TITRE, insertion
- [ ] DemandePiece : charger pièces attendues, fourni=false, valide=false, dateDepot=null
- [ ] Commit / Rollback si erreur