package com.wxp.firstmod.entity.model;

import com.wxp.firstmod.entity.GoldenChickenEntity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/** @author wxp */
public class GoldenChickenModel extends ModelBase {
  private ModelRenderer head;
  private ModelRenderer bill;
  private ModelRenderer chin;
  private ModelRenderer body;
  private ModelRenderer rightLeg;
  private ModelRenderer leftLeg;
  private ModelRenderer rightWing;
  private ModelRenderer leftWing;

  public GoldenChickenModel() {
    this.head = new ModelRenderer(this, 0, 0);
    this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
    this.head.setRotationPoint(0.0F, 15.0F, -4.0F);

    this.bill = new ModelRenderer(this, 14, 0);
    this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
    this.bill.setRotationPoint(0.0F, 15.0F, -4.0F);

    this.chin = new ModelRenderer(this, 14, 4);
    this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
    this.chin.setRotationPoint(0.0F, 15.0F, -4.0F);

    this.body = new ModelRenderer(this, 0, 9);
    this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
    this.body.setRotationPoint(0.0F, 16.0F, 0.0F);

    this.rightLeg = new ModelRenderer(this, 26, 0);
    this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
    this.rightLeg.setRotationPoint(-2.0F, 19.0F, 1.0F);

    this.leftLeg = new ModelRenderer(this, 26, 0);
    this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
    this.leftLeg.setRotationPoint(1.0F, 19.0F, 1.0F);

    this.rightWing = new ModelRenderer(this, 24, 13);
    this.rightWing.addBox(-0.5F, -0.5F, -1.5F, 1, 4, 6);
    this.rightWing.setRotationPoint(-3.0F, 15.5F, 0.0F);

    this.leftWing = new ModelRenderer(this, 24, 13);
    this.leftWing.addBox(-0.5F, -0.5F, -1.5F, 1, 4, 6);
    this.leftWing.setRotationPoint(3.0F, 15.5F, 0.0F);
  }

  @Override
  public void render(
      Entity entityIn,
      float limbSwing,
      float limbSwingAmount,
      float ageInTicks,
      float netHeadYaw,
      float headPitch,
      float scale) {
    this.setRotationAngles(
        limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    this.head.render(scale);
    this.bill.render(scale);
    this.chin.render(scale);
    this.body.render(scale);
    this.rightLeg.render(scale);
    this.leftLeg.render(scale);
    this.rightWing.render(scale);
    this.leftWing.render(scale);
  }

  @Override
  public void setRotationAngles(
      float limbSwing,
      float limbSwingAmount,
      float ageInTicks,
      float netHeadYaw,
      float headPitch,
      float scaleFactor,
      Entity entityIn) {
    double wingSpeed = ((GoldenChickenEntity) entityIn).getWingSpeed();

    this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
    this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
    this.bill.rotateAngleX = this.head.rotateAngleX;
    this.bill.rotateAngleY = this.head.rotateAngleY;
    this.chin.rotateAngleX = this.head.rotateAngleX;
    this.chin.rotateAngleY = this.head.rotateAngleY;
    this.body.rotateAngleX = ((float) Math.PI / 2.0F);
    this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    this.leftLeg.rotateAngleX = -MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    this.rightWing.rotateAngleZ = (float) wingSpeed * ageInTicks;
    this.leftWing.rotateAngleZ = (float) -wingSpeed * ageInTicks;
  }
}
