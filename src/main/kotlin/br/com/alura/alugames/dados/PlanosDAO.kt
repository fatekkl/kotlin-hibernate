package br.com.alura.alugames.dados

import PlanoAssinaturaEntity
import PlanoAvulsoEntity
import PlanoEntity
import br.com.alura.alugames.modelo.Plano
import br.com.alura.alugames.modelo.PlanoAssinatura
import br.com.alura.alugames.modelo.PlanoAvulso
import javax.persistence.EntityManager

class PlanosDAO(manager: EntityManager) : DAO<Plano, PlanoEntity>(manager, PlanoEntity::class.java) {

    override fun toEntity(objeto: Plano): PlanoEntity {
        return if (objeto is PlanoAssinatura) {
            PlanoAssinaturaEntity(
                objeto.tipo,
                objeto.mensalidade,
                objeto.jogosIncluidos,
                objeto.percentualDescontoReputacao,
                objeto.id
            )
        } else {
            PlanoAvulsoEntity(objeto.tipo, objeto.id)
        }
    }

    override fun toModel(entity: PlanoEntity): Plano {
        return if (entity is PlanoAssinaturaEntity) {
            PlanoAssinatura(
                entity.tipo,
                entity.mensalidade,
                entity.jogosIncluidos,
                entity.percentualDescontoReputacao,
                entity.id
            )
        } else {
            PlanoAvulso(entity.tipo, entity.id)
        }
    }
}