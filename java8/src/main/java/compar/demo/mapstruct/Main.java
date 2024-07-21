package compar.demo.mapstruct;





public class Main {
   
    
    public static void main(String[] args) {
        VoUser vo = new VoUser();
        vo.setId(1l);
        vo.setName("zhangsnan");
        vo.setCsrqStr("2001-01-14");
        vo.setSex(0);
        vo.setCreateTime("2024-04-04 12:22:59");

        
        DtoUser dto = UserMapper.INSTANST.voTodto(vo);
        System.out.println(dto.getId());
        System.out.println(dto.getName());
        System.out.println(dto.getCsrq());
        System.out.println(dto.getSex().getName());
        System.out.println(dto.getCreateTime());

        VoUser vo2  = UserMapper.INSTANST.toVo(dto);

        System.out.println(vo2.getId());
        System.out.println(vo2.getName());
        System.out.println(vo2.getCsrqStr());
        System.out.println(vo2.getSex());
        System.out.println(vo2.getCreateTime());
    }
}
