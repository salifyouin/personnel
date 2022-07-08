package ci.dgmp.personnel.security.model.enums;

public enum AssType
{
    ROLE_TO_USER("RoleToUser"), PRIVILEGE_TO_USER("PrivilegeToUser"), PRIVILEGE_TO_ROLE("PrivilegeToRole");
    public String libelle;
    AssType(String libelle)
    {
        this.libelle = libelle;
    }
}
