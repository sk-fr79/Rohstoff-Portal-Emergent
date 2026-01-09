"""
Dashboard Router - Statistiken und Übersicht
"""

from fastapi import APIRouter, Depends

from services.database import get_db
from utils.auth import get_current_user

router = APIRouter(prefix="/api/dashboard", tags=["Dashboard"])


@router.get("/stats")
async def get_dashboard_stats(user = Depends(get_current_user)):
    """Dashboard-Statistiken abrufen"""
    db = get_db()
    mandant_id = user["mandant_id"]
    
    # Zähler
    adressen_count = await db.adressen.count_documents({
        "mandant_id": mandant_id,
        "aktiv": True
    })
    
    artikel_count = await db.artikel.count_documents({
        "mandant_id": mandant_id,
        "aktiv": True
    })
    
    kontrakte_offen = await db.kontrakte.count_documents({
        "mandant_id": mandant_id,
        "status": {"$in": ["OFFEN", "AKTIV"]},
        "deleted": {"$ne": True}
    })
    
    fuhren_offen = await db.fuhren.count_documents({
        "mandant_id": mandant_id,
        "status": {"$in": ["OFFEN", "IN_TRANSPORT"]},
        "deleted": {"$ne": True}
    })
    
    return {
        "adressen": adressen_count,
        "artikel": artikel_count,
        "kontrakte_offen": kontrakte_offen,
        "fuhren_offen": fuhren_offen,
    }
